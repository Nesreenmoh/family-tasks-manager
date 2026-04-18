package com.family.task.dto.entities;

import com.family.task.entity.TaskInstance;

import java.util.List;

public record TaskInstanceDetails(List<TaskInstance> taskInstances) {
}
