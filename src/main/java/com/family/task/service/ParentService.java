package com.family.task.service;

import com.family.task.entity.Parent;
import com.family.task.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParentService {


    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public Parent  addParent(Parent parent){
      return  parentRepository.save(parent);
    }

    public void deleteParent(long id){
        Parent parent = getParentById(id);
        if( parent==null){
            throw new EntityNotFoundException("Parent with id " + id + " not found");
        }
        parentRepository.delete(parent);
    }
    public Parent getParentById(long id) {
        Optional<Parent> parent = parentRepository.findById((id));
        if(!parent.isPresent()){
            throw new EntityNotFoundException("Parent with id " + id + " not found");
        }
        return parent.get();
    }

    public List<Parent> getAllParents(){
        return parentRepository.findAll();
    }


    public Parent updateParent(long id, Parent parent){
        Parent findParent = getParentById(id);
        if( findParent==null){
            throw new EntityNotFoundException("Parent with id " + parent.getId() + " not found");
        }
        findParent.setFName(parent.getFName());
        findParent.setLName(parent.getLName());
        findParent.setEmail(parent.getEmail());
       return parentRepository.save(findParent);
    }
}
