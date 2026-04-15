package com.family.task.mapper;

import com.family.task.dto.ChildRequest;
import com.family.task.dto.ChildResponse;
import com.family.task.entity.Child;

public class ChildObjectMapper {



    public static Child mapChildRequestToChild(ChildRequest childRequest) {
        Child child = new Child();
        child.setFName(childRequest.fName());
        child.setAge(childRequest.age());
        child.setLName(childRequest.lName());
        return child;
    }

    public static ChildResponse mapChildToChildResponse(Child child) {
        return
                new ChildResponse(child.getId(), child.getAge(), child.getFName(), child.getLName());
    }
}
