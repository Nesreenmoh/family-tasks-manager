package com.family.task.mapper;

import com.family.task.dto.entities.ChildrenDetails;
import com.family.task.dto.entities.ChildRequest;
import com.family.task.dto.entities.ChildResponse;
import com.family.task.entity.Child;

import java.util.List;

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

    public static ChildrenDetails mapChildrenToChildDetails(List<Child> children) {
        return new ChildrenDetails(children);
    }
}
