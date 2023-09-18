package com.generation.HelloRESTWorld.model.repositories.abstractions;

import com.generation.HelloRESTWorld.model.Student;

import java.util.Optional;

public interface StudentRepository {

    Iterable<Student> getAllStudents();
    Optional<Student> findStudentById(long id);
    Student create(Student s);
}
