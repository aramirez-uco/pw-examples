package es.uco.pw.demo.model.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CourseRepository extends AbstractRepository{
    
    public CourseRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public int findCourseIdByName(String courseName){
        try{
            String query = sqlQueries.getProperty("select-findCourseIdByName");
            int courseId = jdbcTemplate.query(query, this::extractIdFromRow, courseName);
            return courseId;
        }catch(DataAccessException exception){
            System.err.println("Unable to find course with name=" + courseName);
            exception.printStackTrace();
            return -1;
        }
    }

    public int findProfessorIdByDeparment(String departmentName){
        try{
            String query = sqlQueries.getProperty("select-findProfessorIdByDepartment");
            int professorId = jdbcTemplate.query(query, this::extractIdFromRow, departmentName);
            return professorId;
        }catch(DataAccessException exception){
            System.err.println("Unable to find professor in department=" + departmentName);
            exception.printStackTrace();
            return -1;
        }
    }
            
    public boolean assignProfessorToCourse(int idCourse, int idProfessor){
        try{
            String query = sqlQueries.getProperty("update-assignProfessorToCourse");
            if(query != null){
                int result = jdbcTemplate.update(query, idProfessor, idCourse);
                if (result>0)
                    return true;
                else
                    return false;
            }
            else
                return false;
            } catch(DataAccessException exception){
                System.err.println("Unable to assign professor to course in the database");
                exception.printStackTrace();
            return false;
        }
    }

    public boolean enrolStudentInCourse(int idStudent, int idCourse){
        try{
            String query = sqlQueries.getProperty("insert-enrolStudentInCourse");
            if(query != null){
                int result = jdbcTemplate.update(query, idStudent, idCourse);
                if (result>0)
                    return true;
                else
                    return false;
            }
            else
                return false;
            } catch(DataAccessException exception){
                System.err.println("Unable to enrol student in course in the database");
                exception.printStackTrace();
            return false;
        }
    }

    private int extractIdFromRow(ResultSet row){
        try{
            if(row.first()){
                int id = row.getInt("id");
                return id;
            }
            else{
                return -1;
            }
                       
        } catch (SQLException exception) {
            System.err.println("Unable to retrieve results from the database");
            exception.printStackTrace();
            return -1;
        }
    }

}      