package es.uco.pw.demo.model.domain;

public class Course {
    private int id;
    private String name;
    private String degree;
    private int year;
    private int idProfessor;

    public Course(){
        this.id = -1;
        this.name = null;
        this.degree = null;
        this.year = -1;
        this.idProfessor = -1;
    }

    public Course(String name, String degree, int year){
        this.id = -1;
        this.name = name;
        this.degree = degree;
        this.year = year;
        this.idProfessor = -1;
    }

    public Course(String name, String degree, int year, int idProfessor){
        this.id = -1;
        this.name = name;
        this.degree = degree;
        this.year = year;
        this.idProfessor = idProfessor;
    }

    public Course(int id, String name, String degree, int year, int idProfessor){
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.year = year;
        this.idProfessor = idProfessor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }

    public int getYear() {
        return year;
    }

    public int getIdProfessor(){
        return idProfessor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setIdProfessor(int idProfessor){
        this.idProfessor = idProfessor;
    }

    public String toString(){
        String course = "\tID: " + this.id + "\n\tName: " + this.name + "\n\tDegree: " + this.degree +
        "\n\tYear: " + this.year + "\n\tProfessor ID: " + this.idProfessor;
        return course;
    }
}