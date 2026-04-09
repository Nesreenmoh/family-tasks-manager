package com.family.task.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TaskInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    LocalDate date;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime completedAt;

}
