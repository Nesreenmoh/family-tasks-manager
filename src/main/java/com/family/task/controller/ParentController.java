package com.family.task.controller;


import com.family.task.entity.Parent;
import com.family.task.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parents")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping
    public ResponseEntity<Parent> addParent(@RequestBody Parent parent){
        return ResponseEntity.ok(parentService.addParent(parent));
    }

    @GetMapping
    public ResponseEntity<List<Parent>> getAllParents() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parent> getParentById(@PathVariable long id){
        Parent parent = parentService.getParentById(id);
        return ResponseEntity.ok(parent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParentById(@PathVariable long id){
        Parent parent = parentService.getParentById(id);
        parentService.deleteParent(id);
        return ResponseEntity.ok("Parent Record has been Deleted Successfully");
    }
}
