package com.family.task.service;


import com.family.task.dto.entities.ChildrenDetails;
import com.family.task.dto.entities.ChildRequest;
import com.family.task.dto.entities.ChildResponse;
import com.family.task.entity.Child;
import com.family.task.entity.Parent;
import com.family.task.repository.ChildRepository;
import com.family.task.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.family.task.mapper.ChildObjectMapper.*;

@Service
@Transactional
public class ChildService {


    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;


    public ChildService(ChildRepository childRepository, ParentRepository parentRepository) {
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
    }


    public ChildResponse addChild(long parentId, ChildRequest childRequest) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() ->
            new EntityNotFoundException("Parent with id " + parentId + " not found"));

        Child child = mapChildRequestToChild(childRequest);
        child.setParent(parent);
        parent.addChild(child);
        childRepository.save(child);
        parentRepository.saveAndFlush(parent); // cascades child insert
        return mapChildToChildResponse(child);
    }

    public ChildResponse updateChild(long parentId, long childId, ChildRequest childRequest) {
        Child updatedChild = childRepository.findById(childId).orElseThrow(() ->
                new EntityNotFoundException("Child with id " + childId + " not found"));

        Parent parent = parentRepository.findById(parentId).orElseThrow(() ->
                new EntityNotFoundException("Parent with id " + parentId + " not found"));

        updatedChild.setFName(childRequest.fName());
        updatedChild.setLName(childRequest.lName());
        updatedChild.setAge(childRequest.age());

        return mapChildToChildResponse(updatedChild);
    }

    public void deleteChild(long parentId, long id) {
        Child findChild = childRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Child with this Id " + id + " not found"));

        Parent parent = parentRepository.findById(parentId).orElseThrow(() ->
                new EntityNotFoundException("Parent with id " + parentId + " not found"));

        parent.removeChild(findChild);
        parentRepository.save(parent);
        childRepository.delete(findChild);
    }


    public ChildResponse getChildById(long id) {
        Child child = childRepository.findById(id).orElseThrow( () ->
                new EntityNotFoundException("Child with this Id " + id + " not found"));
        return mapChildToChildResponse(child);

    }


    /*
    Get all children that are belong to specific parent
     */
    public ChildrenDetails getAllChildren(long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() ->
                new EntityNotFoundException("Parent with id " + parentId + " not found"));

       return  mapChildrenToChildDetails(parent
                .getChildren().stream().toList());

    }
}
