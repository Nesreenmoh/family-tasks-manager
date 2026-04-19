package com.family.task.dto.events;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskInstanceCompletedEvent(Long id, String status, LocalDate executionDate, LocalDateTime completedAt, Long taskId, String taskName, String childName) {
}
