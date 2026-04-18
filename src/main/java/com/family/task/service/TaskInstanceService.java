package com.family.task.service;


import com.family.task.dto.entities.TaskInstanceDetails;
import com.family.task.dto.entities.TaskInstanceRequest;
import com.family.task.dto.entities.TaskInstanceResponse;
import com.family.task.entity.*;
import com.family.task.repository.ChildRepository;
import com.family.task.repository.TaskInstanceRepository;
import com.family.task.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.family.task.mapper.TaskInstanceMapper.mapTaskInstanceToTaskInstanceResponse;
import static com.family.task.mapper.TaskInstanceMapper.mapTaskInstancesListToTaskInstanceDetails;

@Service
@Transactional
@AllArgsConstructor
public class TaskInstanceService {

    private final TaskInstanceRepository taskInstanceRepository;
    private final ChildRepository childRepository;
    private final TaskRepository taskRepository;
    private final TaskInstanceEventProducer taskInstanceEventProducer;


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

    public void deleteTaskInstance(long childId, long taskInstanceId) {
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new IllegalArgumentException("Child with id " + childId + " not found"));

        TaskInstance taskInstance = taskInstanceRepository.findById(taskInstanceId).orElseThrow(
                () -> new IllegalArgumentException("Task Instance with id " + taskInstanceId + " not found")
        );
        if(taskInstance.getChild().getId() != child.getId()){
            throw new IllegalArgumentException("Task Instance with id " + taskInstanceId + " does not belong to child with id " + childId);
        }
        taskInstanceRepository.delete(taskInstance);

    }

    public TaskInstanceDetails getTaskInstances(long childId) {
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new IllegalArgumentException("Child with id " + childId + " not found"));

        List<TaskInstance> taskInstanceList = taskInstanceRepository.findByChildId(child.getId());

        return mapTaskInstancesListToTaskInstanceDetails(taskInstanceList);

    }

    public TaskInstanceResponse getTaskInstance(long childId, long taskInstanceId) {
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new IllegalArgumentException("Child with id " + childId + " not found"));

        TaskInstance taskInstance = taskInstanceRepository.findById(taskInstanceId).orElseThrow(
                () -> new IllegalArgumentException("Task Instance with id " + taskInstanceId + " not found")
        );
        return mapTaskInstanceToTaskInstanceResponse(taskInstance);
    }

    public TaskInstanceResponse updateTaskInstance(long childId, long taskInstanceId, TaskInstanceRequest taskInstanceRequest) {
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new IllegalArgumentException("Child with id " + childId + " not found"));

        TaskInstance existingTaskInstance = taskInstanceRepository.findById(taskInstanceId).orElseThrow(
                () -> new IllegalArgumentException("Task Instance with id " + taskInstanceId + " not found")
        );

        if(existingTaskInstance.getChild().getId() != child.getId()){
            throw new IllegalArgumentException("Task Instance with id " + taskInstanceId + " does not belong to child with id " + childId);
        }

        existingTaskInstance.setStatus(TaskStatus.COMPLETED);
        existingTaskInstance.setCompletedAt(LocalDateTime.now());
        taskInstanceRepository.save(existingTaskInstance);
        return mapTaskInstanceToTaskInstanceResponse(existingTaskInstance);
    }


}
