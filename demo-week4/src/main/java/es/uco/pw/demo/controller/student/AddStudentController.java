package es.uco.pw.demo.controller.student;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.domain.StudentType;
import es.uco.pw.demo.model.repository.StudentRepository;

@Controller
public class AddStudentController {

    StudentRepository studentRepository;
        
    public AddStudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.studentRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping("/addStudent")
    public String addStudent() {
        LocalDate date = LocalDate.of(2001, 8, 8);
        Student student = new Student(8, "Hector", "Hermida", 
                                        date, StudentType.FULL_TIME);
        boolean success = studentRepository.addStudent(student);
        String nextPage;
        if(success){
            nextPage = "addStudentViewSucess.html?newStudentId=8";
        }    
        else
            nextPage = "addStudentViewFail.html";
        return new String("redirect:/view/student/" + nextPage);
    }
}