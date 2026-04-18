package com.family.task.mapper;

import com.family.task.dto.entities.RoutineDetails;
import com.family.task.dto.entities.RoutineRequest;
import com.family.task.dto.entities.RoutineResponse;
import com.family.task.entity.Child;
import com.family.task.entity.Routine;

import java.util.List;

public class RoutineObjectMapper {


    public static RoutineResponse mapRoutineToRoutineResponse(Routine routine){
        return new RoutineResponse(routine.getId(), routine.getName(), routine.getChild());
    }


    public static Routine mapRoutineRequestToRoutine(RoutineRequest routineRequest, Child child){
        Routine routine = new Routine();
        routine.setName(routineRequest.name());
        routine.setChild(child);
        return routine;
    }

    public static RoutineDetails  mapRoutineToRoutineDetails(List<Routine> routine){
        return  new RoutineDetails(routine);
    }

}
