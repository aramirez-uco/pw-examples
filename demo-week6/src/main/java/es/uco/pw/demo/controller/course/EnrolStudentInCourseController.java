package es.uco.pw.demo.controller.course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.uco.pw.demo.model.repository.CourseRepository;

@Controller
public class EnrolStudentInCourseController {

    CourseRepository courseRepository;
        
    public EnrolStudentInCourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.courseRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/enrolStudentsInCourse")
    public String enrolStudentsInCourse() {

        // All students must be enrolled in course "Programming"
        int idCourse = this.courseRepository.findCourseIdByName("Programming");
        for(int idStudent=1; idStudent<=6; idStudent++){
            this.courseRepository.enrolStudentInCourse(idStudent, idCourse);
        }

        return new String("home");
    }
    
}