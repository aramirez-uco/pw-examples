package es.uco.pw.demo.controller.professor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import es.uco.pw.demo.model.domain.Department;
import es.uco.pw.demo.model.repository.ProfessorRepository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowDepartmentsController {
    
    ProfessorRepository professorRepository;
    private ModelAndView modelAndView = new ModelAndView();
     
    public ShowDepartmentsController(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.professorRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/showDepartments")
    public ModelAndView getAddProfessorView() {
        this.modelAndView.setViewName("showDepartmentsView.html");
        String deptNames [] = new String []{"Maths and Statistics", "Computer Science and AI", "Physics"};
        List<Department> listOfDepartments = new ArrayList<Department>();
        for(String name: deptNames){
            int numProfs = this.professorRepository.getNumberProfessorsInDepartment(name);
            listOfDepartments.add(new Department(name, numProfs));
        }
        this.modelAndView.addObject("listOfDepartments", listOfDepartments);
        return modelAndView;
    }
}
