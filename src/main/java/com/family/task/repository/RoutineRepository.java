package com.family.task.repository;

import com.family.task.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Query(
            """
                    SELECT r from Routine r WHERE r.id = :routineId
                    AND r.child.id = :childId
                    """
    )
    Optional<Routine> findByIdAndChildId(@Param("routineId") long routineId,@Param("childId") long childId);
}
