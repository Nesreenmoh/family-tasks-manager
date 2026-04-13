package com.family.task.service;


import com.family.task.entity.Child;
import com.family.task.entity.Routine;
import com.family.task.repository.ChildRepository;
import com.family.task.repository.RoutineRepository;
import com.family.task.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final ChildRepository childRepository;

    public RoutineService(RoutineRepository routineRepository, ChildRepository childRepository) {
        this.routineRepository = routineRepository;
        this.childRepository = childRepository;
    }

    public List<Routine> getAllRoutines(long childId) {
        Optional<Child> child = childRepository.findById(childId);
        if(!child.isPresent()) {
            throw new EntityNotFoundException("Child with id " + childId + " not found");
        }

        return child.get()
                .getRoutines()
                .stream().toList();
    }

    public Routine getRoutine(Long id) {

        Optional<Routine> routine = routineRepository.findById(id);
        if(!routine.isPresent()){
            throw new EntityNotFoundException("Routine with id " + id + " not found");
        }
        return routine.get();
    }

    public Routine addRoutine (Routine routine){
        return routineRepository.save(routine);
    }
}
