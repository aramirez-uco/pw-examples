package es.uco.pw.demo.controller.professor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import es.uco.pw.demo.model.domain.Professor;
import es.uco.pw.demo.model.repository.ProfessorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AddProfessorController {
    
    private ProfessorRepository professorRepository;
    private ModelAndView modelAndView = new ModelAndView();
     
    public AddProfessorController(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.professorRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/addProfessor")
    public ModelAndView getAddProfessorView() {
        this.modelAndView.setViewName("addProfessorView.html");
        this.modelAndView.addObject("newProfessor", new Professor());
        return modelAndView;
    }

    @PostMapping("/addProfessor")
    public String addProfessor(@ModelAttribute Professor newProfessor, SessionStatus sessionStatus) {
        
        System.out.println("[AddProfessorController] Received info: id=" + newProfessor.getId() + 
                            " name=" + newProfessor.getName() + 
                            " surname=" + newProfessor.getSurname() + 
                            " department=" + newProfessor.getDepartment());
        boolean success = professorRepository.addProfessor(newProfessor);
        String nextPage;
        
        if(success){
            nextPage = "addProfessorViewSucess.html";
        }    
        else
            nextPage = "addProfessorViewFail.html";
        
        sessionStatus.setComplete();
        return new String("redirect:/view/professor/" + nextPage);
    }
}
