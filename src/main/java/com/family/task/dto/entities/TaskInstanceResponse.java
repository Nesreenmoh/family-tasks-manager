package com.family.task.dto.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskInstanceResponse(Long id, String status, LocalDate executionDate, LocalDateTime completed_at,
                                   Long taskId, String taskName, String childName) {
}
