package com.family.task.dto;

import java.time.LocalTime;

public record TaskRequest(String name, LocalTime scheduledTime) {
}
