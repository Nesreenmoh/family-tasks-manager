package com.family.task.service;


import com.family.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
