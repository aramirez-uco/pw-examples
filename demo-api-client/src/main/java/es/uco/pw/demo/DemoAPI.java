package es.uco.pw.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.domain.StudentType;

public class DemoAPI {

	public static void main(String[] args) {
		sendGetRequests();
		sendPostRequests();
	}

	private static void sendGetRequests(){
		
		RestTemplate rest = new RestTemplate();
		String baseURL = "http://localhost:8080";

		// Request to retrieve all students
		ResponseEntity<Student[]> response = rest.getForEntity(baseURL + "/api/students", Student[].class);
		List<Student> listOfStudents = Arrays.asList(response.getBody());
		System.out.println("==== REQUEST 1: GET all students ====");
		Date date = new Date(response.getHeaders().getDate());
		System.out.println("Response date: " + date);
		for(Student s: listOfStudents){
			System.out.println(s);
			System.out.println("--------------");
		}

		// Request to retrive partial-time students
		response = rest.getForEntity(baseURL + "/api/students?type=PARTIAL_TIME", Student[].class);
		listOfStudents = Arrays.asList(response.getBody());
		System.out.println("==== REQUEST 2: GET partial-time students ====");
		date = new Date(response.getHeaders().getDate());
		System.out.println("Response date: " + date);
		for(Student s: listOfStudents){
			System.out.println(s);
			System.out.println("--------------");
		}

		// Request to retrive one student
		Student student = rest.getForObject(baseURL + "/api/students/{id}", Student.class,1);
		System.out.println("==== REQUEST 3: GET student with id ====");
		System.out.println(student.toString());
	}

	private static void sendPostRequests(){
		RestTemplate rest = new RestTemplate();
		String baseURL = "http://localhost:8080";

		// POST a new student (valid)
		LocalDate birthDate = LocalDate.of(2002, 10, 10);
		Student newStudent = new Student(-1, "Gonzalo", "Garcia", birthDate, StudentType.FULL_TIME);
		ResponseEntity<Student> response;
		
		try{
			response = rest.postForEntity(baseURL + "/api/students", newStudent, Student.class);	
			System.out.println("==== REQUEST 4: POST student (valid) ====");
			System.out.println("Status code: " + response.getStatusCode());
			System.err.println("Response body:\n" + response.getBody());
		}catch(HttpClientErrorException exception){
			System.out.println(exception);
		}

		// POST a student (invalid)
		birthDate = LocalDate.of(2012, 10, 10);
		newStudent.setName("Mathew");
		newStudent.setSurname("Murphy");
		newStudent.setType(StudentType.ERASMUS);
		newStudent.setBirthDate(birthDate);

		System.out.println("==== REQUEST 5: POST student (invalid) ====");
		try{
			response = rest.postForEntity(baseURL + "/api/students", newStudent, Student.class);
		}catch(HttpClientErrorException exception){
			System.out.println(exception);
		}
	}
}
