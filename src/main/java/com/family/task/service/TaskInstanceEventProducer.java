package com.family.task.service;

import com.family.task.dto.events.TaskInstanceCreatedEvent;
import com.family.task.entity.TaskInstance;
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


    public void sendTaskInstanceCreatedEvent(TaskInstance taskInstance) {
        TaskInstanceCreatedEvent taskInstanceCreatedEvent =
                new TaskInstanceCreatedEvent(
                        taskInstance.getId(),
                        taskInstance.getChild().getId(),
                        taskInstance.getTask().getId(),
                        taskInstance.getTask().getName(),
                        taskInstance.getExecutionDate()
                );

        kafkaTemplate.send(TOPIC, taskInstanceCreatedEvent);
        LOGGER.info("Sent TaskInstanceCreatedEvent for TaskInstance with id: {}", taskInstance.getId());
    }


}
