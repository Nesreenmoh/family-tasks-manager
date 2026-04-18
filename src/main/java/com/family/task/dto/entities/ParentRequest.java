package com.family.task.dto.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ParentRequest(
        @NotBlank @Email String email,
        @NotBlank  String fName,
        @NotBlank  String lName
) {
}
