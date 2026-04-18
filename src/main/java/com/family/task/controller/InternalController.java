package com.family.task.controller;

import com.family.task.service.TaskInstanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
Internal / Debug only
 */
@RestController
@RequestMapping("/internal/generate-task-instances")
@AllArgsConstructor
public class InternalController {


    private final TaskInstanceService taskInstanceService;


    @PostMapping
    private ResponseEntity<String> generate(){
        taskInstanceService.generateDailyTaskInstance();
        return ResponseEntity.ok().body("Task instances generated successfully");
    }
}
