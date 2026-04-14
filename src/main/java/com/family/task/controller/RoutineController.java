package com.family.task.controller;

import com.family.task.entity.Routine;
import com.family.task.service.RoutineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/children/{child_id}/routines")
public class RoutineController {

    private final RoutineService routineService;


    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping
    private ResponseEntity<List<Routine>> getRoutines(@PathVariable long child_id) {
       return ResponseEntity.ok(routineService.getAllRoutines(child_id));
    }


    @GetMapping("/{routine_id}")
    private ResponseEntity<Routine> getRoutine(@PathVariable long child_id, @PathVariable Long routine_id) {
       return ResponseEntity.ok( routineService.getRoutine(routine_id));
    }

    @DeleteMapping("{routine_id}")
    private ResponseEntity<String> deleteRoutine(@PathVariable long child_id, @PathVariable Long routine_id) {
        routineService.deleteRoutine(child_id, routine_id);
        return ResponseEntity.ok("Routine has been deleted successfully");
    }

//    @PostMapping
//    private ResponseEntity<Routine> addRoutine(@RequestBody Routine routine){
//        return ResponseEntity.ok( routineService.addRoutine(routine));
//    }


}
