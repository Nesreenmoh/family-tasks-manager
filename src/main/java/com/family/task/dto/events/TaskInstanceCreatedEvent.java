package com.family.task.dto.events;

import java.time.LocalDate;

public record TaskInstanceCreatedEvent(Long taskInstanceId,
                                       Long childId,
                                       Long taskId,
                                       String taskName,
                                       LocalDate executionDate) {
}
