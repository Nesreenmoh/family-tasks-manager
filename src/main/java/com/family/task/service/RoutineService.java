package com.family.task.service;


import com.family.task.repository.TaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoutineService {

    private final TaskRepository taskRepository;

    public RoutineService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
