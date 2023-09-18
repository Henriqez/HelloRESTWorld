package com.generation.HelloRESTWorld.model;

import java.time.LocalDate;

public class Student {

    private long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;

    public Student(long id, String firstname, String lastname, LocalDate birthdate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}
