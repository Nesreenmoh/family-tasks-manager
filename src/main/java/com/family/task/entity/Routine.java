package com.family.task.entity;


import jakarta.persistence.*;
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
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name of the routine must not be empty!")
    private String name;

    @OneToMany( mappedBy = "routine", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();


    @ManyToOne
    private Child child;


}
