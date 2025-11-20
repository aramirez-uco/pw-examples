package es.uco.pw.demo;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import es.uco.pw.demo.model.domain.Course;


public class DemoAPI2 {

	public static void main(String[] args) {
		sendPutRequests();
		sendPatchRequests();
		sendDeleteRequests();
	}

	private static void sendPutRequests(){
		
		RestTemplate rest = new RestTemplate();
		String baseURL = "http://localhost:8080/api";

		// Request to put a course (without response)
		Course newCourse = new Course("Introduction to Maths", "Data Science", 3, 1);
		int id = 2;
		try{
			System.out.println("==== REQUEST 1: PUT (no response) ====");
			rest.put(baseURL + "/courses/{id}", newCourse, id);
			System.out.println("Update correct.");
		}catch(RestClientException exception){
			System.out.println(exception);
		}
	}

	private static void sendPatchRequests(){
		RestTemplate rest = new RestTemplate();
		rest.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		String baseURL = "http://localhost:8080/api";
		int id;

		// Example 1: Request to update one field
		try{
			System.out.println("==== REQUEST 3: PATCH (valid) ====");
			id = 1;
			Course course1 = new Course();
			course1.setYear(2);
			Course response1 = rest.patchForObject(baseURL + "/courses/{id}", course1, Course.class, id);
			System.out.println(response1.toString());
		}catch(RestClientException exception){
			System.out.println(exception);
		}

		// Example 2: Request to update some fields
		try{
			System.out.println("==== REQUEST 4: PATCH (valid) ====");
			id = 3;
			Course course2 = new Course();
			course2.setName("Introduction to Software Engineering");
			course2.setIdProfessor(3);
			Course response2 = rest.patchForObject(baseURL + "/courses/{id}", course2, Course.class, id);
			System.out.println(response2.toString());
		}catch(RestClientException exception){
			System.out.println(exception);
		}

		// Example 3: Invalid request to update the professor (id does not exist)
		try{
			id = 4;
			Course course3 = new Course();
			course3.setIdProfessor(6);
			System.out.println("==== REQUEST 5: PATCH (invalid) ====");
			Course response3 = rest.patchForObject(baseURL + "/courses/{id}", course3, Course.class, id);
			System.out.println(response3.toString());
		}catch(RestClientException exception){
			System.out.println(exception);
		}
	}

	static void sendDeleteRequests(){
		RestTemplate rest = new RestTemplate();
		String baseURL = "http://localhost:8080/api";

		// Example 1: Delete one course
		try{
			System.out.println("==== REQUEST 6: DELETE ONE COURSE (valid) ====");
			rest.delete(baseURL + "/courses/{id}", 1);
		}catch(RestClientException exception){
			System.out.println(exception);
		}

		// Example 2: Delete a course that does not exist
		try{
			System.out.println("==== REQUEST 7: DELETE ONE COURSE (no effect) ====");
			rest.delete(baseURL + "/courses/{id}", 8);
		}catch(RestClientException exception){
			System.out.println(exception);
		}

		// Example 3: Delete all courses
		try{
			System.out.println("==== REQUEST 8: DELETE ALL COURSES (valid) ====");
			rest.delete(baseURL + "/courses");
		}catch(RestClientException exception){
			System.out.println(exception);
		}

		// Example 4: Delete one course that does not exist
		try{
			System.out.println("==== REQUEST 9: DELETE ONE COURSE (no effect) ====");
			rest.delete(baseURL + "/courses/{id}", 2);
		}catch(RestClientException exception){
			System.out.println(exception);
		}
	}
}