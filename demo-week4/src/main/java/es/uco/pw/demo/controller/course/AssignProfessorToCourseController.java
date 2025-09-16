package es.uco.pw.demo.controller.course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.uco.pw.demo.model.repository.CourseRepository;

@Controller
public class AssignProfessorToCourseController {

    CourseRepository courseRepository;
        
    public AssignProfessorToCourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.courseRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/assignProfessorToCourse")
    public String assignProfessorToCourse() {

        // Find course ID by name
        int idCourse = this.courseRepository.findCourseIdByName("Maths");
        
        // Find professor ID by department
        int idProfessor = this.courseRepository.findProfessorIdByDeparment("Maths and Statistics");
        
        // Update foreign key
        if(idCourse!=-1 && idProfessor != -1){
            boolean success = this.courseRepository.assignProfessorToCourse(idCourse, idProfessor);
            System.out.println("[AssignProfessorCourseController] Professor (id=" 
                + idProfessor + ") assigned to course (id=" + idCourse + "): " + success);
        }
        else{
            System.out.println("[AssignProfessorCourseController] Professor or course not found");
        }

        return new String("home");
    }
}