package com.family.task.repository;

import com.family.task.entity.TaskInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskInstanceRepository extends JpaRepository<TaskInstance, Long> {
}
