package com.family.task.service;

import com.family.task.dto.entities.ParentDetailsResponse;
import com.family.task.dto.entities.ParentRequest;
import com.family.task.dto.entities.ParentResponse;
import com.family.task.entity.Parent;
import com.family.task.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.family.task.mapper.ParentObjectMapper.*;

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
        Optional<Parent> findParent = parentRepository.findById(id);
        if( !findParent.isPresent()){
            throw new EntityNotFoundException("Parent with id " + id + " not found");
        }
        parentRepository.delete(findParent.get());
    }
    public ParentDetailsResponse getParentById(long id) {
        Optional<Parent> parent = parentRepository.findById((id));
        if(!parent.isPresent()){
            throw new EntityNotFoundException("Parent with id " + id + " not found");
        }
        return mapParentToParentDetailsResponse(parent.get());
    }

    public List<Parent> getAllParents(){
        return parentRepository.findAll();
    }


    public ParentResponse updateParent(long id, ParentRequest parentRequest){
        Optional<Parent> findParent = parentRepository.findById(id);
        if( !findParent.isPresent()){
            throw new EntityNotFoundException("Parent with id " + id + " not found");
        }
        findParent.get().setFName(parentRequest.fName());
        findParent.get().setLName(parentRequest.lName());
        findParent.get().setEmail(parentRequest.email());
        parentRepository.save(findParent.get());
        return mapParentToParentResponse(findParent.get());
    }
}
