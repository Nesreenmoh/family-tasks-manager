package com.family.task.mapper;

import com.family.task.dto.ParentRequest;
import com.family.task.dto.ParentResponse;
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
}
