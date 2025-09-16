package es.uco.pw.demo.model.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uco.pw.demo.model.domain.Professor;


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
}



            