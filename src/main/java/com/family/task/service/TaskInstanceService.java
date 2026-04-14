package com.family.task.service;


import com.family.task.repository.TaskInstanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskInstanceService {

    private final TaskInstanceRepository taskInstanceRepository;

    public TaskInstanceService(TaskInstanceRepository taskInstanceRepository) {
        this.taskInstanceRepository = taskInstanceRepository;
    }
}
