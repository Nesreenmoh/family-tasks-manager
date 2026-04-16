package com.family.task.mapper;

import com.family.task.dto.TaskRequest;
import com.family.task.dto.TaskResponse;
import com.family.task.dto.TaskRoutineDetails;
import com.family.task.entity.Task;

import java.util.List;

public class TaskObjectMapper {


    public static Task mapTaskRequestToTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setName(taskRequest.name());
        task.setScheduledTime(taskRequest.scheduledTime());
        return task;
    }

    public static TaskResponse mapTaskToTaskResponse(Task task) {
        return new TaskResponse(task.getId(), task.getName(), task.getScheduledTime());
    }


    public static TaskRoutineDetails mapTasksToTaskRoutineDetails(List<Task> tasks) {
        return new TaskRoutineDetails(tasks);
    }
}
