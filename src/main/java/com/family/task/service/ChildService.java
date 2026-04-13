package com.family.task.service;


import com.family.task.entity.Child;
import com.family.task.entity.Parent;
import com.family.task.repository.ChildRepository;
import com.family.task.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChildService {


    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;


    public ChildService(ChildRepository childRepository, ParentRepository parentRepository) {
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
    }


    public Child addChild(long parentId, Child child) {
        Optional<Parent> parent = parentRepository.findById(parentId);
        if (!parent.isPresent()) {
            throw new EntityNotFoundException("Parent with id " + parentId + " not found");
        }
        parent.get().addChild(child);
        parentRepository.save(parent.get()); // cascades child insert
        return child;
    }

    public Child updateChild(long parentid, long childId, Child child) {
        Child updatedChild = getChildById(childId);
        if (updatedChild == null) {
            throw new EntityNotFoundException("Child with id " + child.getId() + " not found");
        }

        Optional<Parent> parent = parentRepository.findById(parentid);
        if (!parent.isPresent()) {
            throw new EntityNotFoundException("Parent with id " + parentid + " not found");
        }
        updatedChild.setFName(child.getFName());
        updatedChild.setLName(child.getLName());
        updatedChild.setAge(child.getAge());

        return updatedChild;
    }

    public void deleteChild(long parentId, long id) {
        Child findChild = getChildById(id);

        if (findChild == null) {
            throw new EntityNotFoundException("Child with this Id " + id + " not found");
        }
        Optional<Parent> parent = parentRepository.findById(parentId);
        if (parent == null) {
            throw new EntityNotFoundException("Parent with this Id " + id + " not found");
        }

        parent.get().removeChild(findChild);
        parentRepository.save(parent.get());
        childRepository.delete(findChild);
    }


    public Child getChildById(long id) {
        Optional<Child> child = childRepository.findById(id);

        if (!child.isPresent()) {
            throw new EntityNotFoundException("Child with this Id " + id + " not found");
        }
        return child.get();
    }


    /*
    Get all children that are belong to specific parent
     */
    public List<Child> getAllChildren(long parentId) {
        Optional<Parent> parent = parentRepository.findById(parentId);
        if (!parent.isPresent()) {
            throw new EntityNotFoundException("Parent with this Id " + parentId + " not found");
        }
        return parent.get().getChildren()
                .stream()
                .filter(child -> child.getParent().getId().equals(parentId))
                .toList();

    }
}
