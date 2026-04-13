package com.family.task.controller;

import com.family.task.entity.Routine;
import com.family.task.service.RoutineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routines")
public class RoutineController {

    private final RoutineService routineService;


    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping
    private ResponseEntity<List<Routine>> getRoutines(){
       return ResponseEntity.ok(routineService.getAllRoutines());
    }


    @GetMapping("/{id}")
    private ResponseEntity<Routine> getRoutine(@PathVariable Long id){
       return ResponseEntity.ok( routineService.getRoutine(id));
    }



}
