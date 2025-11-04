package es.uco.pw.demo.controller.professor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowStudentsController {
    
    StudentRepository studentRepository;
    private ModelAndView modelAndView = new ModelAndView();
     
    public ShowStudentsController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.studentRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/showStudents")
    public ModelAndView getShowStudentView() {
        this.modelAndView.setViewName("showStudentsView.html");
        List<Student> listOfStudents = this.studentRepository.findAllStudents();
        this.modelAndView.addObject("listOfStudents", listOfStudents);
        return modelAndView;
    }
}
