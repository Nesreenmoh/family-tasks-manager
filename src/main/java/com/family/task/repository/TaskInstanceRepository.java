package com.family.task.repository;

import com.family.task.entity.TaskInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskInstanceRepository extends JpaRepository<TaskInstance, Long> {

   List<TaskInstance> findByChildId(Long childId);
   boolean existsByChildIdAndTaskIdAndExecutionDate(Long childId,  Long taskId, LocalDate executionDate);
}
