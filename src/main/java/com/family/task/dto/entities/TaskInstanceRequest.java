package com.family.task.dto.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskInstanceRequest(String status, LocalDateTime  completed_at, LocalDate executionDate, Long taskId) {
}
