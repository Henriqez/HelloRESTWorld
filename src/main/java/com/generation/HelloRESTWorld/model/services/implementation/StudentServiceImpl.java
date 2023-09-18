package com.generation.HelloRESTWorld.model.services.implementation;

import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.services.abstraction.StudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Iterable<Student> getAllStudents() {
        return null;
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        return Optional.empty();
    }

    @Override
    public Student create(Student s) {
        return null;
    }
}
