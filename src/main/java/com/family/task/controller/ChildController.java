package com.family.task.controller;

import com.family.task.dto.entities.ChildRequest;
import com.family.task.dto.entities.ChildResponse;
import com.family.task.dto.entities.ChildrenDetails;
import com.family.task.service.ChildService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parents/{parentId}/children")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping
    public ResponseEntity<ChildResponse> addChild(@PathVariable long parentId, @RequestBody @Valid ChildRequest childRequest) {
        return ResponseEntity.ok(childService.addChild(parentId, childRequest));
    }

    @GetMapping
    public ResponseEntity<ChildrenDetails> findAllChildren(@PathVariable long parentId) {
        return ResponseEntity.ok(childService.getAllChildren(parentId));
    }


    @GetMapping("/{childId}")
    public ResponseEntity<ChildResponse> findChildById(@PathVariable long parentId, @PathVariable long childId) {
        return ResponseEntity.ok(childService.getChildById(childId));
    }

    @DeleteMapping("/{childId}")
    public ResponseEntity<String> deleteChildById(@PathVariable long parentId, @PathVariable long childId) {
        childService.deleteChild(parentId, childId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{childId}")
    public ResponseEntity<ChildResponse> updateChildById(@PathVariable long parentId, @PathVariable long childId, @RequestBody @Valid ChildRequest child) {

        return ResponseEntity.ok(childService.updateChild(parentId, childId, child));
    }
}
