package com.family.task.service.producer;

import com.family.task.dto.events.TaskInstanceCompletedEvent;
import com.family.task.dto.events.TaskInstanceCreatedEvent;
import com.family.task.entity.TaskInstance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/*
This event triggered when TaskInstance -> Saved
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TaskInstanceEventProducer {



    private final String CREATED_TOPIC = "task_instance_created";
    private final String COMPLETED_TOPIC = "task_instance_completed";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendTaskInstanceCreatedEvent(TaskInstance taskInstance) {


        try {
            TaskInstanceCreatedEvent taskInstanceCreatedEvent =
                    new TaskInstanceCreatedEvent(
                            taskInstance.getId(),
                            taskInstance.getChild().getId(),
                            taskInstance.getTask().getId(),
                            taskInstance.getTask().getName(),
                            taskInstance.getExecutionDate()
                    );

            String payload = objectMapper.writeValueAsString(taskInstanceCreatedEvent);
            kafkaTemplate.send(CREATED_TOPIC, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }


        log.info("Sent TaskInstanceCreatedEvent for TaskInstance with id: {}", taskInstance.getId());
    }


    public void taskCompletedEvent(TaskInstance taskInstance) {
        try
        {
            TaskInstanceCompletedEvent taskInstanceCompletedEvent =
                    new TaskInstanceCompletedEvent(
                            taskInstance.getId(),
                            "Completed",
                            taskInstance.getExecutionDate(),
                            taskInstance.getCompletedAt(),
                            taskInstance.getTask().getId(),
                            taskInstance.getTask().getName(),
                            taskInstance.getChild().getFName()+" "+ taskInstance.getChild().getLName()
                    );
            String payload = objectMapper.writeValueAsString(taskInstanceCompletedEvent);
            kafkaTemplate.send(COMPLETED_TOPIC, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

}
