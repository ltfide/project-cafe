package com.cafe.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafe.entities.bank.Bank;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BankService {

   @Autowired
   private ModelMapper modelMapper;

   private static final ObjectMapper objectMapper = new ObjectMapper();

   public List<?> getItemFromJson() throws IOException {
      InputStream inputStream = new FileInputStream(new File("src/main/resources/banks.json"));
      TypeReference<List<?>> typeReference = new TypeReference<List<?>>() {
      };
      return objectMapper.readValue(inputStream, typeReference);
   }

   public ResponseEntity<?> getAll() {
      try {
         Bank[] bank = modelMapper.map(getItemFromJson(), Bank[].class);
         return ResponseEntity.ok(bank);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

   public Optional<Bank> filterByCodeName(String bankCode) throws IOException {
      Bank[] banks = modelMapper.map(getItemFromJson(), Bank[].class);
      System.out.println(banks);

      List<Bank> list = List.of(banks);

      return list.stream()
            .filter(bank -> bank.getBank_code().equals(bankCode))
            .findFirst();
   }

}
