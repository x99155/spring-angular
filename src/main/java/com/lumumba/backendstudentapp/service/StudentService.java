package com.lumumba.backendstudentapp.service;

import com.lumumba.backendstudentapp.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudent();

    Student getStudent(String id);
}
