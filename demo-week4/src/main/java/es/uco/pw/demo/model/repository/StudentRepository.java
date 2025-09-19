package es.uco.pw.demo.model.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.domain.StudentType;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository extends AbstractRepository{

    public StudentRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> findAllStudents(){
        try{
            String query = sqlQueries.getProperty("select-findAllStudents");
            if(query != null){
                List<Student> result = jdbcTemplate.query(query, new RowMapper<Student>(){
                public Student mapRow(ResultSet rs, int rowNumber) throws SQLException{
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        Date.valueOf(rs.getString("birthdate")).toLocalDate(),
                        StudentType.valueOf(rs.getString("type")));
                    };
                });
                return result;
            }
            else
                return null;
        }catch(DataAccessException exception){
            System.err.println("Unable to find students");
            exception.printStackTrace();
            return null;
        }
    }

    public Student findStudentById(int id){
        try{
            String query = sqlQueries.getProperty("select-findStudentById");
            Student result = jdbcTemplate.query(query, this::mapRowToStudent, id);
            if (result != null)
                return result;
            else
                return null;
        }catch(DataAccessException exception){
            System.err.println("Unable to find student with id=" + id);
            exception.printStackTrace();
            return null;
        }
    }

            
    private Student mapRowToStudent(ResultSet row){
        try{
            if(row.first()){
                int id = row.getInt("id");
                String name = row.getString("name");
                String surname = row.getString("surname");
                Date birthDateInTable = Date.valueOf(row.getString("birthdate"));
                StudentType type = StudentType.valueOf(row.getString("type"));

                Student student = new Student(id, name, surname, birthDateInTable.toLocalDate(), type);
                return student;
            }
            else{
                return null;
            }
                       
        } catch (SQLException exception) {
            System.err.println("Unable to retrieve results from the database");
            exception.printStackTrace();
            return null;
        }
    }

    public List<Student> findStudentsByType(StudentType type){
        try{
            String query = sqlQueries.getProperty("select-findStudentByType");
            if(query != null){
                List<Student> result = jdbcTemplate.query(query, new RowMapper<Student>(){
                public Student mapRow(ResultSet rs, int rowNumber) throws SQLException{
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        Date.valueOf(rs.getString("birthdate")).toLocalDate(),
                        type);
                    };
                }, type.toString());
                return result;
            }
            else
                return null;    
        }catch(DataAccessException exception){
            System.err.println("Unable to find student with type=" + type.toString());
            exception.printStackTrace();
            return null;
        }
    }

    public int getNumberStudentsByType(StudentType type){
        String query = sqlQueries.getProperty("select-findStudentByType");
        if(query != null){
            try{
                int result = jdbcTemplate.query(query, this::countRowsStudentType, type.toString());
                return result;
            }catch(DataAccessException exception){
                System.err.println("Unable to find student with type=" + type.toString());
                exception.printStackTrace();
                return -1;
            }
        }
        else
         return -1;
    }

    private int countRowsStudentType(ResultSet row){
        try{
            int numberOfStudents = 0;
            while(row.next()){
                numberOfStudents++;
            }
            return numberOfStudents;                   
        } catch (SQLException exception) {
            System.err.println("Unable to retrieve results from the database");
            exception.printStackTrace();
            return -1;
        }
    }

    public boolean addStudent(Student student){
        try{
            String query = sqlQueries.getProperty("insert-addStudent");
            if(query != null){
                int result = jdbcTemplate.update(query, 
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getBirthDate().toString(),
                student.getType().toString());
                if (result>0)
                    return true;
                else
                    return false;
            }
            else
                return false;
            } catch(DataAccessException exception){
                System.err.println("Unable to insert student in the database");
            return false;
        }
    }
}



            