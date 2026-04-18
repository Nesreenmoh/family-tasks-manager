package com.family.task.service;


import com.family.task.dto.entities.RoutineDetails;
import com.family.task.dto.entities.RoutineRequest;
import com.family.task.dto.entities.RoutineResponse;
import com.family.task.entity.Child;
import com.family.task.entity.Routine;
import com.family.task.repository.ChildRepository;
import com.family.task.repository.RoutineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.family.task.mapper.RoutineObjectMapper.*;

@Repository
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final ChildRepository childRepository;

    public RoutineService(RoutineRepository routineRepository, ChildRepository childRepository) {
        this.routineRepository = routineRepository;
        this.childRepository = childRepository;
    }

    public RoutineDetails getAllRoutines(long childId) {
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new EntityNotFoundException("Child with id " + childId + " not found"));


        List<Routine> listOfRoutines = child.getRoutines()
                .stream().toList();

        return mapRoutineToRoutineDetails(listOfRoutines);
    }

    public RoutineResponse getRoutine(long child_id, long id) {

        Child child = childRepository.findById(child_id).orElseThrow(
                () -> new EntityNotFoundException("Child with id " + child_id + " not found")
        );

        Routine routine = routineRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Routine with id " + id + " not found"));


        return mapRoutineToRoutineResponse(routine);


    }

    public RoutineResponse addRoutine(long child_id, RoutineRequest routineRequest) {
        Child child = childRepository.findById(child_id).orElseThrow(
                () -> new EntityNotFoundException("Child with id " + child_id + " not found")
        );

        Routine routine = mapRoutineRequestToRoutine(routineRequest, child);

        Routine savedRoutine = routineRepository.save(routine);

        return mapRoutineToRoutineResponse(savedRoutine);
    }

    public void deleteRoutine(long child_id, long routine_id) {
        Routine routine = routineRepository.findByIdAndChildId(routine_id, child_id)
                .orElseThrow(() -> new EntityNotFoundException("Routine/Child is not found!"));
        routineRepository.delete(routine);
    }

    public RoutineResponse updateRoutine(long childId, long routine_id, RoutineRequest routineRequest) {
        Child child = childRepository.findById(childId).orElseThrow(
                () -> new EntityNotFoundException("Child with id " + childId + " not found")
        );

        Routine existingRoutine = routineRepository.findById(routine_id).orElseThrow(() ->
                new EntityNotFoundException("Routine with id " + routine_id + " not found"));

        existingRoutine.setName(routineRequest.name());
        Routine savedRoutine = routineRepository.save(existingRoutine);
        return mapRoutineToRoutineResponse(savedRoutine);

    }
}
