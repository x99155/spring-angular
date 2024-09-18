package com.lumumba.backendstudentapp.service;

import com.lumumba.backendstudentapp.entity.Payment;
import com.lumumba.backendstudentapp.enums.PaymentStatus;
import com.lumumba.backendstudentapp.enums.PaymentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayments();

    Payment getPayment(Long id);

    List<Payment> getByCode(String code);

    Payment addPayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException;

    byte[] getPaymentFile(Long id) throws IOException;

    Payment updatePaymentStatus(Long id, PaymentStatus status);

}
