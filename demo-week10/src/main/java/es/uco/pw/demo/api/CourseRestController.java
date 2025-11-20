package es.uco.pw.demo.api;

import es.uco.pw.demo.model.domain.Course;
import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.repository.CourseRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping(path="/api/courses", produces="application/json")
public class CourseRestController {

    CourseRepository courseRepository;
    
    public CourseRestController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.courseRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @PutMapping(path="/{id}", consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public void putCourse(@PathVariable int id, @RequestBody Course requestCourse) {
        try{
            // Get course by id
            Course currentCourse = this.courseRepository.findCourseById(id);
            if(currentCourse != null){
                requestCourse.setId(currentCourse.getId());
                boolean resultOk = courseRepository.updateCourse(requestCourse);
                if(resultOk){
                    System.out.println("[CourseRestController]\n\tCourse update correct...");
                }
                else{
                    System.out.println("[CourseRestController]\n\tCourse update incorrect...");
                }
            }
            else{
                System.out.println("[CourseRestController]\n\tCourse not found...");
                
            }
        }catch(Exception e){
            System.out.println("[CourseRestController]\n\tDatabase exception...");
        }
    }
    
    @PatchMapping(path="/{id}", consumes="application/json")
    public Course patchCourse(@PathVariable int id, @RequestBody Course requestCourse) {
       try{
            // Get course by id
            Course currentCourse = this.courseRepository.findCourseById(id);
            if(currentCourse != null){
                
                // Update properties
                requestCourse.setId(currentCourse.getId());
                
                if(requestCourse.getName() != null){
                    currentCourse.setName(requestCourse.getName());
                }

                if(requestCourse.getDegree() != null){
                    currentCourse.setDegree(requestCourse.getDegree());
                }

                if(requestCourse.getYear() != -1){
                    currentCourse.setYear(requestCourse.getYear());
                }

                if(requestCourse.getIdProfessor() != -1){
                    currentCourse.setIdProfessor(requestCourse.getIdProfessor());
                }

                // Save updated resource
                boolean resultOk = courseRepository.updateCourse(currentCourse);
                if(resultOk){
                     return currentCourse;
                }
                else{
                     return requestCourse;
                }
            }
            else{
                 return requestCourse;
                
            }
        }catch(Exception e){
             return requestCourse;
        }
    }
}