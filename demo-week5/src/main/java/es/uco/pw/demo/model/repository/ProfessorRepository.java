package es.uco.pw.demo.model.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uco.pw.demo.model.domain.Professor;
import es.uco.pw.demo.model.domain.Student;
import es.uco.pw.demo.model.domain.StudentType;


@Repository
public class ProfessorRepository extends AbstractRepository{
    
    public ProfessorRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean addProfessor(Professor professor){
        try{
            String query = sqlQueries.getProperty("insert-addProfessor");
            if(query != null){
                int result = jdbcTemplate.update(query, 
                professor.getId(),
                professor.getName(),
                professor.getSurname(),
                professor.getDepartment());
                if (result>0)
                    return true;
                else
                    return false;
            }
            else
                return false;
            } catch(DataAccessException exception){
                System.err.println("Unable to insert professor in the database");
            return false;
        }
    }

    public Professor findProfessorById(int id){
        try{
            String query = sqlQueries.getProperty("select-findProfessorById");
            Professor result = jdbcTemplate.query(query, this::mapRowToProfessor, id);
            if (result != null)
                return result;
            else
                return null;
        }catch(DataAccessException exception){
            System.err.println("Unable to find professor with id=" + id);
            exception.printStackTrace();
            return null;
        }
    }

    private Professor mapRowToProfessor(ResultSet row){
        try{
            if(row.first()){
                int id = row.getInt("id");
                String name = row.getString("name");
                String surname = row.getString("surname");
                String department = row.getString("department");
                Professor professor = new Professor(id, name, surname, department);
                return professor;
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
}



            