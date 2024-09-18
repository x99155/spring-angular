package com.lumumba.backendstudentapp.service.impl;

import com.lumumba.backendstudentapp.entity.Payment;
import com.lumumba.backendstudentapp.entity.Student;
import com.lumumba.backendstudentapp.enums.PaymentStatus;
import com.lumumba.backendstudentapp.enums.PaymentType;
import com.lumumba.backendstudentapp.repository.PaymentRepository;
import com.lumumba.backendstudentapp.repository.StudentRepository;
import com.lumumba.backendstudentapp.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public List<Payment> getByCode(String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @Override
    public Payment addPayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        // on crée le repertoire user/home/student-app-files/payments si il n'existe pas
        Path path = Paths.get(System.getProperty("user.home"), "student-app-files", "payments");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // génère un nom aléatoire pour le fichier
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "student-app-files", "payments", fileName +".pdf");

        // on enregistre le fichier envoyé dans la requete dans ce chemin crée précédement
        Files.copy(file.getInputStream(), filePath);

        // charge l'utilisateur qui correspond au code recu dans la requete
        Student student = studentRepository.findByCode(studentCode);

        // enregistre un payment
        Payment payment = Payment.builder()
                .date(LocalDate.now())
                .amount(amount)
                .type(type)
                .status(PaymentStatus.CREATED)
                .file(filePath.toUri().toString())
                .student(student)
                .build();

        return paymentRepository.save(payment);
    }


    // méthode pour consulter un fichier
    @Override
    public byte[] getPaymentFile(Long id) throws IOException {
        // charge le payment correspondant à l'id recu dans la requete
        Payment payment = paymentRepository.findById(id).get();

        // récupère le chemin du fichier que tu stock dans une variable
        String filePath = payment.getFile();

        return Files.readAllBytes(Path.of(URI.create(filePath)));
    }

    @Override
    public Payment updatePaymentStatus(Long id, PaymentStatus status) {

        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);

        return paymentRepository.save(payment);
    }
}
