package com.family.task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parent extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Email
    @NotBlank(message = "Email must not be empty!")
    private String email;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Child> children = new HashSet<Child>();

}
