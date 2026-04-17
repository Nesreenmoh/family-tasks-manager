package com.family.task.controller;


import com.family.task.dto.TaskInstanceDetails;
import com.family.task.dto.TaskInstanceRequest;
import com.family.task.dto.TaskInstanceResponse;
import com.family.task.service.TaskInstanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/children/{childId}/taskInstances")
public class TaskInstanceController {

    private final TaskInstanceService taskInstanceService;


    public TaskInstanceController(TaskInstanceService taskInstanceService) {
        this.taskInstanceService = taskInstanceService;
    }


    @PostMapping
    private ResponseEntity<TaskInstanceResponse> addTaskInstance(@PathVariable long childId, @RequestBody TaskInstanceRequest taskInstanceRequest) {
        return ResponseEntity.ok(taskInstanceService.addTaskInstance(childId, taskInstanceRequest));
    }


    @DeleteMapping("{taskInstance_id}")
    private ResponseEntity<TaskInstanceResponse> deleteTaskInstance(@PathVariable long childId,@PathVariable long taskInstance_id) {
        taskInstanceService.deleteTaskInstance(childId, taskInstance_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    private ResponseEntity<TaskInstanceDetails> getTaskInstances(@PathVariable long childId) {
        return  ResponseEntity.ok(taskInstanceService.getTaskInstances(childId));
    }

    @GetMapping("{taskInstanceId}")
    private ResponseEntity<TaskInstanceResponse> getTaskInstances(@PathVariable long childId,  @PathVariable long taskInstanceId) {
        return  ResponseEntity.ok(taskInstanceService.getTaskInstance(childId, taskInstanceId));
    }

    @PatchMapping("{taskInstanceId}/complete")
    private ResponseEntity<TaskInstanceResponse> updateTaskInstance(@PathVariable long childId,  @PathVariable long taskInstanceId, @RequestBody TaskInstanceRequest taskInstanceRequest) {
        return  ResponseEntity.ok(taskInstanceService.updateTaskInstance(childId, taskInstanceId, taskInstanceRequest));
    }


}
