package com.family.task.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(name="idx_taskinstance_child", columnList = "child_id"),
        @Index(name="idx_taskinstance_task", columnList = "task_id")
})
public class TaskInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate executionDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;


//    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}
