package com.cafe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.entities.JobPosition;
import com.cafe.repositories.JobPositionRepository;

@Service
public class JobPositionService {

   @Autowired
   private JobPositionRepository jobPositionRepository;

   public Iterable<JobPosition> getAll() {
      return jobPositionRepository.findAll();
   }

}
