package com.family.task.service.consumer;


import com.family.task.dto.events.TaskInstanceCompletedEvent;
import com.family.task.dto.events.TaskInstanceCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.Counter;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskInstanceEventConsumer {

    private final String CREATED_TASKS_TOPIC = "task_instance_created";
    private final String COMPLETED_TASKS_TOPIC = "task_instance_completed";
    private final ObjectMapper objectMapper;
    private final MeterRegistry meterRegistry;

    private Counter completedTasksCounter;
    private Counter createdTasksCounter;

    @PostConstruct
    public void init() {
        completedTasksCounter = Counter.builder("tasks_completed_total")
                .description("Total number of completed tasks")
                .register(meterRegistry);

        createdTasksCounter = Counter.builder("tasks_created_total")
                .description("Total number of created tasks")
                .register(meterRegistry);
    }


    @KafkaListener(topics = CREATED_TASKS_TOPIC, groupId = "family_task_created_group")
    public void consumeCreatedTasks(String message){

        try {

            TaskInstanceCreatedEvent taskInstanceCreatedEvent = objectMapper.readValue(message, TaskInstanceCreatedEvent.class);
            createdTasksCounter.increment();
            log.info("Received TaskInstanceCreatedEvent for TaskInstance with id: {}", taskInstanceCreatedEvent);
        }catch (Exception e){
            log.error("Error while consuming TaskInstanceCreatedEvent", e);
        }
    }

    @KafkaListener(topics = COMPLETED_TASKS_TOPIC, groupId = "family_task_completed_group")
    public void consumeCompletedTasks(String message){

        try {

            TaskInstanceCompletedEvent taskInstanceCreatedEvent = objectMapper.readValue(message, TaskInstanceCompletedEvent.class);
            completedTasksCounter.increment();
            log.info("Notify Parent: Child {} completed task  {}",taskInstanceCreatedEvent.childName(), taskInstanceCreatedEvent);
        }catch (Exception e){
            log.error("Error while consuming TaskInstanceCompletedEvent", e);
        }
    }
}
