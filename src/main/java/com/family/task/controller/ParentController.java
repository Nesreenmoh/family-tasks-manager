package com.family.task.controller;


import com.family.task.dto.ParentDetailsResponse;
import com.family.task.dto.ParentRequest;
import com.family.task.dto.ParentResponse;
import com.family.task.entity.Parent;
import com.family.task.service.ParentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ParentResponse> addParent(@RequestBody @Valid ParentRequest parent){
        return ResponseEntity.ok(parentService.addParent(parent));
    }

    @GetMapping
    public ResponseEntity<List<Parent>> getAllParents() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDetailsResponse> getParentById(@PathVariable long id){
        ParentDetailsResponse parentResponse = parentService.getParentById(id);
        return ResponseEntity.ok(parentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteParentById(@PathVariable long id){
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentResponse> updateParent(@PathVariable long id, @RequestBody @Valid ParentRequest parentRequest){
        return
                ResponseEntity.ok(parentService.updateParent(id, parentRequest));
    }
}
