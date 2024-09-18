package com.lumumba.backendstudentapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder @ToString
@Entity
@Table(name = "students")
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String code;
    private String urlImg;
}
