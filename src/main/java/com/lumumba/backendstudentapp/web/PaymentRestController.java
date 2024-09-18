package com.lumumba.backendstudentapp.web;

import com.lumumba.backendstudentapp.entity.Payment;
import com.lumumba.backendstudentapp.enums.PaymentStatus;
import com.lumumba.backendstudentapp.enums.PaymentType;
import com.lumumba.backendstudentapp.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
public class PaymentRestController {

    private final PaymentService paymentService;

    @GetMapping("/payments")
    public List<Payment> getPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/payments/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    @GetMapping("/students/{code}/payment")
    public List<Payment> getByCode(@PathVariable String code) {
        return paymentService.getByCode(code);
    }

    @PostMapping(value = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {

        return paymentService.addPayment(file, date, amount, type, studentCode);

    }

    // on précise au navigateur que les données envoyés (produce) est un fichier pdf
    @GetMapping(value = "/payments/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long id) throws IOException {

        return paymentService.getPaymentFile(id);
    }

    @PutMapping("/payments/updateStatus/{id}")
    public Payment updateStatus(@PathVariable Long id, @RequestParam PaymentStatus status) {
        return paymentService.updatePaymentStatus(id, status);
    }
}
