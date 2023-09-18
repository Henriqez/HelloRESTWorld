package com.generation.HelloRESTWorld.controllers;

import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.services.abstraction.StudentService;
import com.generation.HelloRESTWorld.model.services.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping(value = "/student")
    public Iterable<Student> getAllStudents(){
        Student s1 = new Student(1 , "Mario", "Rossi", LocalDate.of(1990 ,2 ,20));
        Student s2 = new Student(2, "luigi", "morandi", LocalDate.of(1995,5,15));

        Collection<Student> students = new ArrayList<>();

        students.add(s1);
        students.add(s2);

        return students;
    }
}
