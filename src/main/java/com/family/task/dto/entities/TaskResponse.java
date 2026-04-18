package com.family.task.dto.entities;

import java.time.LocalTime;

public record TaskResponse(Long  id, String name, LocalTime scheduledTime) {
}
