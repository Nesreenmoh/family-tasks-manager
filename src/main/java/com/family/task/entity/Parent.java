package com.family.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Parent extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Email
    @NotBlank(message = "Email must not be empty!")
    private String email;


}
