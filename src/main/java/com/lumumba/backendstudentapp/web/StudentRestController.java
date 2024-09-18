package com.lumumba.backendstudentapp.web;

import com.lumumba.backendstudentapp.entity.Payment;
import com.lumumba.backendstudentapp.entity.Student;
import com.lumumba.backendstudentapp.enums.PaymentStatus;
import com.lumumba.backendstudentapp.enums.PaymentType;
import com.lumumba.backendstudentapp.repository.PaymentRepository;
import com.lumumba.backendstudentapp.repository.StudentRepository;
import com.lumumba.backendstudentapp.service.PaymentService;
import com.lumumba.backendstudentapp.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
public class StudentRestController {

    private  final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable String id) {
        return studentService.getStudent(id);
    }



}
