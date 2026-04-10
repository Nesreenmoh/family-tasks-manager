package com.family.task.service;


import com.family.task.repository.ChildRepository;
import org.springframework.stereotype.Service;

@Service
public class ChildService {


    private final ChildRepository childRepository;


    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }
}
