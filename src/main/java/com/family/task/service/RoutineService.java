package com.family.task.service;


import com.family.task.entity.Routine;
import com.family.task.repository.RoutineRepository;
import com.family.task.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoutineService {

    private final RoutineRepository routineRepository;

    public RoutineService(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public List<Routine> getAllRoutines() {
        return routineRepository.findAll();
    }

    public Routine getRoutine(Long id) {

        Optional<Routine> routine = routineRepository.findById(id);
        if(!routine.isPresent()){
            throw new EntityNotFoundException("Routine with id " + id + " not found");
        }
        return routine.get();
    }
}
