package es.uco.pw.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.uco.pw.demo.model.Student;
import es.uco.pw.demo.model.StudentRepository;
import es.uco.pw.demo.model.StudentType;

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

        // Testing student repository and database access
     
        // Find all students
        List<Student> listOfStudents = studentRepository.findAllStudents();
        if(listOfStudents!=null)
            System.err.println("[HomeController] Number of students in the database: " + listOfStudents.size());
        
        // Add student
        LocalDate date = LocalDate.of(2002, 7, 27);
        Student student = new Student(7, "Gloria", "Garcia", date, StudentType.PARTIAL_TIME);
        boolean success = studentRepository.addStudent(student);
        System.out.println("[HomeController] Student added to the database: " + success);

        // Find student by ID
        int id = 7;
        Student insertedStudent = studentRepository.findStudentById(id);            
        if (insertedStudent!=null){
            System.out.println("[HomeController] Student with id=" + id + " found:");
            System.out.println(
                "\tName: " + insertedStudent.getName() + 
                "\n\tSurname: " + insertedStudent.getSurname() + 
                "\n\tBirth date: " + insertedStudent.getBirthDate().toString() + 
                "\n\tType: " + insertedStudent.getType().toString());
        }

        // Find students by type
        List<Student> erasmusStudents = studentRepository.findStudentsByType(StudentType.ERASMUS);
        if(erasmusStudents!=null){
            System.out.println("List of Erasmus students:");
            erasmusStudents.forEach((s) -> System.out.println("\tName: " + s.getName() + " Surname: " + s.getSurname()));
        }

        // Count students by type
        int numberOfPartialStudents = studentRepository.getNumberStudentsByType(StudentType.PARTIAL_TIME);
        System.out.println("Number of partial-time students: " + numberOfPartialStudents);

        return new String("home");
    }
    
}