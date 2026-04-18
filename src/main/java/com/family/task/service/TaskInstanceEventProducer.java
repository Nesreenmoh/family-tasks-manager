package com.family.task.service;

import com.family.task.dto.events.TaskInstanceCreatedEvent;
import com.family.task.entity.TaskInstance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
/*
This event triggered when TaskInstance -> Saved
 */
@Service
@RequiredArgsConstructor
public class TaskInstanceEventProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskInstanceEventProducer.class);

    private final String TOPIC = "task_instance_created";

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
            kafkaTemplate.send(TOPIC, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }


        LOGGER.info("Sent TaskInstanceCreatedEvent for TaskInstance with id: {}", taskInstance.getId());
    }


}
