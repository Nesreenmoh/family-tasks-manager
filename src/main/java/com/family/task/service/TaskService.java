package com.family.task.service;


import com.family.task.dto.entities.TaskRequest;
import com.family.task.dto.entities.TaskResponse;
import com.family.task.dto.entities.TaskRoutineDetails;
import com.family.task.entity.Routine;
import com.family.task.entity.Task;
import com.family.task.repository.RoutineRepository;
import com.family.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.family.task.mapper.TaskObjectMapper.*;

@Service
@Transactional
public class TaskService {


    private final TaskRepository taskRepository;
    private final RoutineRepository routineRepository;

    public TaskService(TaskRepository taskRepository, RoutineRepository routineRepository) {
        this.taskRepository = taskRepository;
        this.routineRepository = routineRepository;
    }

    public TaskResponse addTask(long routineId, TaskRequest taskRequest) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()
                -> new IllegalArgumentException("Routine with id " + routineId + " does not exist"));

        Task task = mapTaskRequestToTask(taskRequest);
        routine.getTasks().add(task);
        routineRepository.save(routine);
        return mapTaskToTaskResponse(taskRepository.save(task));


    }

    public TaskRoutineDetails getTasks(long routineId) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()
                -> new IllegalArgumentException("Routine with id " + routineId + " does not exist"));

        return mapTasksToTaskRoutineDetails(routine.getTasks().stream().toList());

    }

    public TaskResponse getTask(long routineId, long taskId) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()
                -> new IllegalArgumentException("Routine with id " + routineId + " does not exist"));


        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new IllegalArgumentException("Task with id " + taskId + " does not exist"));

        return mapTaskToTaskResponse(task);
    }

    public void deleteTask(long routineId, long taskId) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()
                -> new IllegalArgumentException("Routine with id " + routineId + " does not exist"));


        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new IllegalArgumentException("Task with id " + taskId + " does not exist"));

        routine.removeTask(task);
        routineRepository.save(routine);
        taskRepository.delete(task);

    }

    public TaskResponse updateTask(long routineId, long taskId, TaskRequest taskRequest) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()
                -> new IllegalArgumentException("Routine with id " + routineId + " does not exist"));


        Task existingTask = taskRepository.findById(taskId).orElseThrow(() ->
                new IllegalArgumentException("Task with id " + taskId + " does not exist"));

        existingTask.setScheduledTime(taskRequest.scheduledTime());
        existingTask.setName(taskRequest.name());
        taskRepository.save(existingTask);
        return mapTaskToTaskResponse(existingTask);

    }
}
