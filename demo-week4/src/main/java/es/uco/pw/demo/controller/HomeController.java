package es.uco.pw.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.uco.pw.demo.model.repository.StudentRepository;

@Controller
public class HomeController {

    StudentRepository studentRepository;
        
    public HomeController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.studentRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/")
    public String home() {

        return new String("home");
    }
    
}