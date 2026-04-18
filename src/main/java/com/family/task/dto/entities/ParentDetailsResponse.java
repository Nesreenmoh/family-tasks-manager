package com.family.task.dto.entities;

import com.family.task.entity.Child;

import java.util.Set;

public record ParentDetailsResponse(Long id, String fName, String lName, Set<Child> children) {
}
