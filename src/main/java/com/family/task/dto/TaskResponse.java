package com.family.task.dto;

import com.family.task.entity.Routine;

import java.time.LocalTime;

public record TaskResponse(Long  id, String name, LocalTime scheduledTime) {
}
