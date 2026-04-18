package com.family.task.controller;


import com.family.task.dto.entities.TaskRequest;
import com.family.task.dto.entities.TaskResponse;
import com.family.task.dto.entities.TaskRoutineDetails;
import com.family.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/routines/{routineId}/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    private ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest, @PathVariable long routineId) {
        return ResponseEntity.ok(taskService.addTask(routineId, taskRequest));
    }

    @PutMapping("{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable long routineId ,@PathVariable long taskId, @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.updateTask(routineId, taskId, taskRequest));
    }

    @GetMapping
    private ResponseEntity<TaskRoutineDetails> getTask(@PathVariable long routineId) {
        return ResponseEntity.ok(taskService.getTasks(routineId));
    }

    @GetMapping("{taskId}")
    private ResponseEntity<TaskResponse> getTask(@PathVariable long routineId, @PathVariable long taskId) {
        return ResponseEntity.ok(taskService.getTask(routineId, taskId));
    }

    @DeleteMapping("{taskId}")
    private ResponseEntity<TaskResponse> deleteTask(@PathVariable long routineId, @PathVariable long taskId) {
        taskService.deleteTask(routineId, taskId);
        return ResponseEntity.noContent().build();
    }
}
