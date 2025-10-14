package es.uco.pw.demo.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.repository.StudentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AddStudentController {

    private StudentRepository studentRepository;
    private ModelAndView modelAndView = new ModelAndView();

    public AddStudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.studentRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/addStudent")
    public ModelAndView getAddStudentView() {
        this.modelAndView.setViewName("addStudentView.html");
        this.modelAndView.addObject("newStudent", new Student());
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(@ModelAttribute Student newStudent) {
        
        String nextPage;
        Integer idStudent = studentRepository.getStudentIdIfExists(newStudent.getName(), newStudent.getSurname());
        if(idStudent != -1){
            newStudent.setId(idStudent);
            nextPage = "addStudentViewFail.html";
        }
        
        else{
            int nextId = studentRepository.findAllStudents().size() + 1;
            newStudent.setId(nextId);
            boolean success = studentRepository.addStudent(newStudent);
            if(success){
                nextPage = "addStudentViewSucess.html";
            }    
            else{
                nextPage = "/addStudentViewFail.html";
            }
        }
        this.modelAndView.setViewName(nextPage);
        this.modelAndView.addObject("student", newStudent);
        return this.modelAndView;
    }
}