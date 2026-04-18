package com.family.task.service;


import com.family.task.entity.*;
import com.family.task.repository.ChildRepository;
import com.family.task.repository.TaskInstanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class TaskInstanceScheduler {


    private final ChildRepository childRepository;
    private final TaskInstanceRepository taskInstanceRepository;
    private final TaskInstanceEventProducer  taskInstanceEventProducer;


    @Scheduled(cron = "0 0 0 * * *") // This will run every day at midnight
    public void generateDailyTaskInstance() {
        LocalDate today = LocalDate.now();

        List<Child> children = childRepository.findAll();

        for (Child child : children) {
            for (Routine routine : child.getRoutines()) {
                for (Task task : routine.getTasks()) {
                    if (!taskInstanceRepository.existsByChildIdAndTaskIdAndExecutionDate(child.getId(),task.getId(), today)) {
                        createTaskInstance(child, task, today);
                    }
                }
            }
        }

    }

    private void createTaskInstance(Child child, Task task, LocalDate today) {
        TaskInstance taskInstance = TaskInstance
                .builder()
                .child(child)
                .task(task)
                .executionDate(today)
                .status(TaskStatus.PENDING)
                .build();

        TaskInstance createdTaskInstance = taskInstanceRepository.save(taskInstance);

        taskInstanceEventProducer.sendTaskInstanceCreatedEvent(createdTaskInstance);
    }
}
