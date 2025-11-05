package es.uco.pw.demo.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.domain.StudentType;
import es.uco.pw.demo.model.repository.StudentRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping(path="/api/students", produces="application/json")
public class StudentRestController {

    StudentRepository studentRepository;
    
    public StudentRestController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.studentRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentRepository.findAllStudents();
        ResponseEntity<List<Student>> response = new ResponseEntity<>(students, HttpStatus.OK);
        return response;
    }

    @GetMapping(params="type")
    public ResponseEntity<List<Student>> getStudentsByType(@RequestParam String type) {
        List<Student> students = studentRepository.findStudentsByType(StudentType.valueOf(type));
        ResponseEntity<List<Student>> response = new ResponseEntity<>(students, HttpStatus.OK);
        return response;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id){
        Student student = studentRepository.findStudentById(id);            
        ResponseEntity<Student> response;
        if(student != null){
            response = new ResponseEntity<>(student, HttpStatus.OK);
        }
        else{
            response = new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /*
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student) {
        boolean resultOk = studentRepository.addStudent(student);
        if(resultOk)
            return student;
        else
            return null;
    }*/

    @PostMapping(consumes="application/json")
    public ResponseEntity<Student> postStudent(@RequestBody Student student) {
        ResponseEntity<Student> response;
        Integer idStudent = studentRepository.getStudentIdIfExists(student.getName(), student.getSurname());
        if(idStudent ==-1){
            if(student.getBirthDate().getYear() > 2009){
                response = new ResponseEntity<>(student, HttpStatus.UNPROCESSABLE_ENTITY);
            }
            else{
                int nextId = studentRepository.findAllStudents().size() + 1;
                student.setId(nextId);
                boolean resultOk = studentRepository.addStudent(student);
                if(resultOk){
                    response = new ResponseEntity<>(student, HttpStatus.CREATED);
                }
                else{
                   response = new ResponseEntity<>(student, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        else{
            response = new ResponseEntity<>(student, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }
}