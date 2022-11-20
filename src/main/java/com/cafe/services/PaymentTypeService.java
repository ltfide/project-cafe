package com.cafe.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.entities.PaymentType;
import com.cafe.repositories.PaymentTypeRepository;

@Service
public class PaymentTypeService {

   @Autowired
   private PaymentTypeRepository paymentTypeRepository;

   public Iterable<PaymentType> getAll() {
      return paymentTypeRepository.findAll();
   }

   public PaymentType findById(UUID id) {
      Optional<PaymentType> data = paymentTypeRepository.findById(id);

      if (data == null) {
         return null;
      }

      return data.get();
   }

}
