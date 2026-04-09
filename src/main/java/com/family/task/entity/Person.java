package com.family.task.entity;


import jakarta.validation.constraints.NotBlank;

public class Person {

    @NotBlank(message = "First Name must not be empty!")
    private String fName;

    @NotBlank(message = "Last Name must not be empty!")
    private String lName;

}
