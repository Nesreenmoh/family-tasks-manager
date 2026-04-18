package com.family.task.service;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskInstanceScheduler {

    private final TaskInstanceService taskInstanceService;

    @Scheduled(cron = "0 0 0 * * *") // This will run every day at midnight
    public void generateDailyTaskInstance(){
        taskInstanceService.generateDailyTaskInstance();
    }

}
