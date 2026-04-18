package com.family.task.mapper;

import com.family.task.dto.entities.TaskInstanceDetails;
import com.family.task.dto.entities.TaskInstanceRequest;
import com.family.task.dto.entities.TaskInstanceResponse;
import com.family.task.entity.TaskInstance;
import com.family.task.entity.TaskStatus;

import java.util.List;

public class TaskInstanceMapper {


    public static TaskInstance mapTaskInstanceRequestToTaskInstance(TaskInstanceRequest taskInstanceRequest) {
        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setCompletedAt(taskInstanceRequest.completed_at());
        taskInstance.setStatus(TaskStatus.valueOf(taskInstanceRequest.status()));
        taskInstance.setExecutionDate(taskInstanceRequest.executionDate());
        return taskInstance;

    }

    public static TaskInstanceResponse mapTaskInstanceToTaskInstanceResponse(TaskInstance taskInstance) {
        return new TaskInstanceResponse(
                taskInstance.getId(),
                taskInstance.getStatus().name(),
                taskInstance.getExecutionDate(),
                taskInstance.getCompletedAt(),
                taskInstance.getTask().getId(),
                taskInstance.getTask().getName(),
                taskInstance.getChild().getFName()+" "+ taskInstance.getChild().getLName()
        );
    }

    public static TaskInstanceDetails mapTaskInstancesListToTaskInstanceDetails(List<TaskInstance> taskInstances) {
        return new TaskInstanceDetails(taskInstances);
    }
}
