package com.family.task.mapper;

import com.family.task.dto.entities.ParentDetailsResponse;
import com.family.task.dto.entities.ParentRequest;
import com.family.task.dto.entities.ParentResponse;
import com.family.task.entity.Parent;

public class ParentObjectMapper {



    public static ParentResponse mapParentToParentResponse(Parent parent){
       return  new ParentResponse(parent.getId(),
                parent.getFName(),
                parent.getLName(), parent.getEmail());
    }

    public static Parent mapParentRequestToParent(ParentRequest parentRequest){

        Parent parent = new Parent();
        parent.setFName(parentRequest.fName());
        parent.setLName(parentRequest.lName());
        parent.setEmail(parentRequest.email());
        return parent ;
    }

    public static ParentDetailsResponse  mapParentToParentDetailsResponse(Parent parent){
       return  new ParentDetailsResponse(parent.getId()
                , parent.getFName(), parent.getLName(),
                parent.getChildren());
    }
}
