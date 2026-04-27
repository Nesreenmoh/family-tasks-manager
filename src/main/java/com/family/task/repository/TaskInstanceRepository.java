package com.family.task.repository;

import com.family.task.entity.TaskInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskInstanceRepository extends JpaRepository<TaskInstance, Long> {

   List<TaskInstance> findByChildId(Long childId);


   @Query("""
SELECT COUNT(ti) > 0 FROM TaskInstance ti WHERE ti.child.id =:childId AND ti.task.id = :taskId AND ti.executionDate = :today
""")

   boolean existsByChildIdAndTaskIdAndExecutionDate(@Param("childId") Long childId, @Param("taskId") Long taskId, @Param("today") LocalDate executionDate);
}
