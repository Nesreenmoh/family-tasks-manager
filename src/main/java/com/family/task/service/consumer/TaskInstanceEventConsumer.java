package com.family.task.service.consumer;


import com.family.task.dto.events.TaskInstanceCompletedEvent;
import com.family.task.dto.events.TaskInstanceCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskInstanceEventConsumer {

    private final String CREATED_TASKS_TOPIC = "task_instance_created";
    private final String COMPLETED_TASKS_TOPIC = "task_instance_completed";
    private final ObjectMapper objectMapper;


    @KafkaListener(topics = CREATED_TASKS_TOPIC, groupId = "family_task_created_group")
    public void consumeCreatedTasks(String message){

        try {

            TaskInstanceCreatedEvent taskInstanceCreatedEvent = objectMapper.readValue(message, TaskInstanceCreatedEvent.class);
            log.info("Received TaskInstanceCreatedEvent for TaskInstance with id: {}", taskInstanceCreatedEvent);
        }catch (Exception e){
            log.error("Error while consuming TaskInstanceCreatedEvent", e);
        }
    }

    @KafkaListener(topics = COMPLETED_TASKS_TOPIC, groupId = "family_task_completed_group")
    public void consumeCompletedTasks(String message){

        try {

            TaskInstanceCompletedEvent taskInstanceCreatedEvent = objectMapper.readValue(message, TaskInstanceCompletedEvent.class);
            log.info("Notify Parent: Child {} completed task  {}",taskInstanceCreatedEvent.childName(), taskInstanceCreatedEvent);
        }catch (Exception e){
            log.error("Error while consuming TaskInstanceCompletedEvent", e);
        }
    }
}
