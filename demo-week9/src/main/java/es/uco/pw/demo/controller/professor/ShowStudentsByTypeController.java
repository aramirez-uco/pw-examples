package es.uco.pw.demo.controller.professor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.domain.StudentType;
import es.uco.pw.demo.model.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowStudentsByTypeController {
    
    StudentRepository studentRepository;
    private ModelAndView modelAndView = new ModelAndView();
     
    public ShowStudentsByTypeController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.studentRepository.setSQLQueriesFileName(sqlQueriesFileName);
        this.modelAndView.setViewName("searchStudentsByTypeView.html");       
    }

    @GetMapping("/searchStudentsByType")
    public ModelAndView searchStudentsByType(@RequestParam(value = "selectedType", defaultValue = "none") String selectedType) {
        if(!selectedType.equals("none")){
            List<Student> listOfStudents = this.studentRepository.findStudentsByType(StudentType.valueOf(selectedType));
            this.modelAndView.addObject("listOfStudents", listOfStudents);
        }
        return this.modelAndView;
    }
}