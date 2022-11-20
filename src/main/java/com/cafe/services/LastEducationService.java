package com.cafe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.entities.LastEducation;
import com.cafe.repositories.LastEducationRepository;

@Service
public class LastEducationService {

   @Autowired
   private LastEducationRepository lastEducationRepository;

   public Iterable<LastEducation> getAll() {
      return lastEducationRepository.findAll();
   }

}
