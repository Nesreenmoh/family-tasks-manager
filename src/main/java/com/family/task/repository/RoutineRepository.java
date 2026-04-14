package com.family.task.repository;

import com.family.task.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {


    Optional<Routine> findByIdAndChildId(long child_id, long routine_id);
}
