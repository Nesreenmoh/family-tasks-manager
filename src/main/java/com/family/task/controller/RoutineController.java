package com.family.task.controller;

import com.family.task.dto.entities.RoutineDetails;
import com.family.task.dto.entities.RoutineRequest;
import com.family.task.dto.entities.RoutineResponse;
import com.family.task.service.RoutineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/children/{child_id}/routines")
public class RoutineController {

    private final RoutineService routineService;


    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping
    private ResponseEntity<RoutineDetails> getRoutines(@PathVariable long child_id) {
       return ResponseEntity.ok(routineService.getAllRoutines(child_id));
    }

    @GetMapping("/{routine_id}")
    private ResponseEntity<RoutineResponse> getRoutine(@PathVariable long child_id, @PathVariable Long routine_id) {
       return ResponseEntity.ok( routineService.getRoutine(child_id,routine_id));
    }

    @DeleteMapping("{routine_id}")
    private ResponseEntity<String> deleteRoutine(@PathVariable long child_id, @PathVariable Long routine_id) {
        routineService.deleteRoutine(child_id, routine_id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    private ResponseEntity<RoutineResponse> addRoutine(@PathVariable long child_id, @RequestBody RoutineRequest routineRequest){
        return ResponseEntity.ok( routineService.addRoutine(child_id, routineRequest));
    }

    @PutMapping("{routine_id}")
    private ResponseEntity<RoutineResponse> updateRoutine(@PathVariable long child_id,  @PathVariable Long routine_id, @RequestBody RoutineRequest routineRequest){
        return ResponseEntity.ok( routineService.updateRoutine(child_id, routine_id, routineRequest));
    }


}
