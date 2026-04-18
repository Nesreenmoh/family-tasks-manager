package com.family.task.dto.entities;

import java.time.LocalTime;

public record TaskRequest(String name, LocalTime scheduledTime) {
}
