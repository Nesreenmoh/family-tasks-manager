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
        Optional<Parent> findParent = parentRepository.findById(id);
        if( !findParent.isPresent()){
            throw new EntityNotFoundException("Parent with id " + id + " not found");
        }
        parentRepository.delete(findParent.get());
    }
    public ParentResponse getParentById(long id) {
        Optional<Parent> parent = parentRepository.findById((id));
        if(!parent.isPresent()){
            throw new EntityNotFoundException("Parent with id " + id + " not found");
        }
        return mapParentToParentResponse(parent.get());
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
