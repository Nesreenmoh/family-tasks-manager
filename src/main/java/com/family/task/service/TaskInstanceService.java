package com.family.task.service;


import com.family.task.dto.TaskInstanceDetails;
import com.family.task.dto.TaskInstanceRequest;
import com.family.task.dto.TaskInstanceResponse;
import com.family.task.entity.Child;
import com.family.task.entity.TaskInstance;
import com.family.task.entity.TaskStatus;
import com.family.task.repository.ChildRepository;
import com.family.task.repository.TaskInstanceRepository;
import com.family.task.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


//    public TaskInstanceResponse addTaskInstance(long childId, TaskInstanceRequest taskInstanceRequest) {
//        Child child = childRepository.findById(childId).orElseThrow(() ->
//                new IllegalArgumentException("Child with id " + childId + " not found"));
//
//        Task task = taskRepository.findById(taskInstanceRequest.taskId()).orElseThrow(
//                () -> new IllegalArgumentException("Task with id " + taskInstanceRequest.taskId() + " not found")
//        );
//
//        TaskInstance taskInstance = mapTaskInstanceRequestToTaskInstance(taskInstanceRequest);
//        taskInstance.setChild(child);
//        taskInstance.setTask(task);
//        taskInstanceRepository.save(taskInstance);
//        return mapTaskInstanceToTaskInstanceResponse(taskInstance);
//    }

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
