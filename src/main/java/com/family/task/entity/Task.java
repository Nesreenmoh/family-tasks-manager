package com.family.task.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Task name must not be empty!")
    private String name;

    private LocalTime scheduledTime;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;


}
