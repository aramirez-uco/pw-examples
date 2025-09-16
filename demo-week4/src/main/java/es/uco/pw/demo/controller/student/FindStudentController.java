package es.uco.pw.demo.controller.student;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.repository.StudentRepository;

@Controller
public class FindStudentController {

    StudentRepository studentRepository;
        
    public FindStudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.studentRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/findStudentById")
    public ModelAndView findStudentById(){
        Random randomGenerator = new Random();
        int id = randomGenerator.nextInt(1, 7);
        Student student = studentRepository.findStudentById(id);            
        ModelAndView model = new ModelAndView("findStudentById.html");
        model.addObject("student", student);
        return model;
    }
}
