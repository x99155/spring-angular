package com.lumumba.backendstudentapp.service.impl;

import com.lumumba.backendstudentapp.entity.Student;
import com.lumumba.backendstudentapp.repository.StudentRepository;
import com.lumumba.backendstudentapp.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(String id) {
        return studentRepository.findById(id).get();
    }
}
