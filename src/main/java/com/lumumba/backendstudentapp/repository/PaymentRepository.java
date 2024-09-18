package com.lumumba.backendstudentapp.repository;

import com.lumumba.backendstudentapp.entity.Payment;
import com.lumumba.backendstudentapp.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // méthodes personalisées
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
}
