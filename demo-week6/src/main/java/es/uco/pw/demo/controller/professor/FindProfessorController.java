package es.uco.pw.demo.controller.professor;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import es.uco.pw.demo.model.domain.Professor;
import es.uco.pw.demo.model.repository.ProfessorRepository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindProfessorController {
    
    ProfessorRepository professorRepository;
     
    public FindProfessorController(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.professorRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

     @GetMapping("/findProfessorById")
    public ModelAndView findProfessorById(){
        Random randomGenerator = new Random();
        int id = randomGenerator.nextInt(1, 3);
        Professor professor = professorRepository.findProfessorById(id);            
        ModelAndView model = new ModelAndView("findProfessorByIdView.html");
        model.addObject("professor", professor);
        return model;
    }
}
