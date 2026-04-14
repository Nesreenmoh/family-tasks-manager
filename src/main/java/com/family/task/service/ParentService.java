package com.family.task.service;

import com.family.task.dto.ParentRequest;
import com.family.task.dto.ParentResponse;
import com.family.task.entity.Parent;
import com.family.task.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.family.task.mapper.ParentObjectMapper.mapParentRequestToParent;
import static com.family.task.mapper.ParentObjectMapper.mapParentToParentResponse;

@Service
@Transactional
public class ParentService {


    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public ParentResponse addParent(ParentRequest parentRequest){
       Parent parent =  mapParentRequestToParent(parentRequest);
      return mapParentToParentResponse(parentRepository.save(parent));
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
