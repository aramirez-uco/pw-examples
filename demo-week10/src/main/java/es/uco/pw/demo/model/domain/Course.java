package es.uco.pw.demo.model.domain;

public class Course {
    private Integer id;
    private String name;
    private String degree;
    private Integer year;
    private Integer idProfessor;

    public Course(){
        this.id = null;
        this.name = null;
        this.degree = null;
        this.year = -1;
        this.idProfessor = null;
    }
    
    public Course(String name, String degree, Integer year){
        this.id = null;
        this.name = name;
        this.degree = degree;
        this.year = year;
        this.idProfessor = null;
    }

    public Course(String name, String degree, Integer year, Integer idProfessor){
        this.id = -1;
        this.name = name;
        this.degree = degree;
        this.year = year;
        this.idProfessor = idProfessor;
    }

    public Course(int id, String name, String degree, Integer year, Integer idProfessor){
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.year = year;
        this.idProfessor = idProfessor;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getIdProfessor(){
        return idProfessor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setYear(Integer year){
        this.year = year;
    }

    public void setIdProfessor(Integer idProfessor){
        this.idProfessor = idProfessor;
    }

    public String toString(){
        String course = "\tID: " + this.id + "\n\tName: " + this.name + "\n\tDegree: " + this.degree +
        "\n\tYear: " + this.year + "\n\tProfessor ID: " + this.idProfessor;
        return course;
    }
}