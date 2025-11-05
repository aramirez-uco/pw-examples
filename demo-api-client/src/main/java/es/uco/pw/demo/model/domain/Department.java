package es.uco.pw.demo.model.domain;

public class Department {
    private String name;
    private int numProfessors;
    
    public Department(){

    }
    
    public Department(String name, int numProfessors) {
        this.name = name;
        this.numProfessors = numProfessors;
    }

    public String getName() {
        return name;
    }

    public int getNumProfessors() {
        return numProfessors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumProfessors(int numProfessors) {
        this.numProfessors = numProfessors;
    }
}
