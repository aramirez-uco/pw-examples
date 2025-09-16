package es.uco.pw.demo.model;

import java.time.LocalDate;

public class Student {
    
    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private StudentType type;
    
    public Student(int id, String name, String surname, LocalDate birthDate, StudentType type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public StudentType getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public void setType(StudentType type) {
        this.type = type;
    }
}