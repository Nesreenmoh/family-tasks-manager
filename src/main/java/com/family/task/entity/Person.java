package com.family.task.entity;


import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Person {

    @NotBlank(message = "First Name must not be empty!")
    private String fName;

    @NotBlank(message = "Last Name must not be empty!")
    private String lName;

}
