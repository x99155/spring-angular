package com.lumumba.backendstudentapp;

import com.lumumba.backendstudentapp.entity.Payment;
import com.lumumba.backendstudentapp.entity.Student;
import com.lumumba.backendstudentapp.enums.PaymentStatus;
import com.lumumba.backendstudentapp.enums.PaymentType;
import com.lumumba.backendstudentapp.repository.PaymentRepository;
import com.lumumba.backendstudentapp.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor // injection des dépendances
@SpringBootApplication
public class BackendStudentAppApplication {

	private final StudentRepository studentRepository;
	private final PaymentRepository paymentRepository;

	// insertion des données au démarrage de l'application

	@Bean
	CommandLineRunner init() {
        return args -> {
			// create 3 student
			Student st1 = Student.builder()
					.id(UUID.randomUUID().toString())
					.code("112233")
					.firstName("Boris")
					.build();

			Student st2 = Student.builder()
					.id(UUID.randomUUID().toString())
					.code("445566")
					.firstName("Juana")
					.build();

			Student st3 = Student.builder()
					.id(UUID.randomUUID().toString())
					.code("778899")
					.firstName("Arthur")
					.build();

			studentRepository.save(st1);
			studentRepository.save(st2);
			studentRepository.save(st3);

			// create payment

			PaymentType[] paymentTypes = PaymentType.values(); // crée un table de payment
			Random random = new Random();

			studentRepository.findAll()
					.forEach(student -> {
						for (int i = 0; i < 10; i++) {
							int index = random.nextInt(paymentTypes.length);
							Payment payment = Payment.builder()
									.date(LocalDate.now())
									.amount(100+(int)(Math.random()*100))
									.type(paymentTypes[index])
									.student(st1)
									.status(PaymentStatus.CREATED)
									.file(UUID.randomUUID().toString())
									.build();

							paymentRepository.save(payment);
						}
					});


		};
    }


	public static void main(String[] args) {
		SpringApplication.run(BackendStudentAppApplication.class, args);
	}

}
