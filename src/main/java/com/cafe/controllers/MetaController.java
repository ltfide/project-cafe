package com.cafe.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entities.Gender;
import com.cafe.entities.GenderList;
import com.cafe.entities.JobPosition;
import com.cafe.entities.LastEducation;
import com.cafe.entities.PaymentType;
import com.cafe.entities.bank.Bank;
import com.cafe.services.BankService;
import com.cafe.services.JobPositionService;
import com.cafe.services.LastEducationService;
import com.cafe.services.PaymentTypeService;
import com.cafe.services.WilayahService;

@RestController
@RequestMapping("/api/meta")
public class MetaController {

   @Autowired
   private JobPositionService jobPositionService;

   @Autowired
   private LastEducationService lastEducationService;

   @Autowired
   private WilayahService wilayahService;

   @Autowired
   private BankService bankService;

   @Autowired
   private PaymentTypeService paymentTypeService;

   @GetMapping("/jobPosition")
   public Iterable<JobPosition> getAllJobPosition() {
      return jobPositionService.getAll();
   }

   @GetMapping("/lastEducation")
   public Iterable<LastEducation> getAllLastEducation() {
      return lastEducationService.getAll();
   }

   @GetMapping("/province")
   public ResponseEntity<?> getAllProvinceList() {
      return wilayahService.getAllProvince();
   }

   @GetMapping("/city/{provinceId}")
   public ResponseEntity<?> getCityByProvinceId(@PathVariable("provinceId") int provinceId) {
      return wilayahService.getCity(provinceId);
   }

   @GetMapping("/district/{regencyId}")
   public ResponseEntity<?> getDistrictByCityId(@PathVariable("regencyId") int regencyId) {
      return wilayahService.getDistrict(regencyId);
   }

   @GetMapping("/village/{districtId}")
   public ResponseEntity<?> getVillageByDistrictId(@PathVariable("districtId") int districtId) {
      return wilayahService.getVillage(districtId);
   }

   @GetMapping("/gender")
   public ResponseEntity<Object> getAllGender() {
      List<Object> allGender = new ArrayList<>();

      for (Gender gender : Gender.values()) {
         allGender.add(new GenderList(gender.name().charAt(0), gender));
      }
      return ResponseEntity.ok(allGender);
   }

   @GetMapping("/bank")
   public ResponseEntity<?> getAllBank() throws IOException {
      return bankService.getAll();
   }

   @GetMapping("/bank/{bankCode}")
   public ResponseEntity<Object> getBankByBankCode(@PathVariable("bankCode") String bankCode) throws IOException {
      Optional<Bank> bank = bankService.filterByCodeName(bankCode);

      if (!bank.isPresent()) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message",
               "Data Not found"));
      }
      return ResponseEntity.ok(bank);
   }

   @GetMapping("/paymentType")
   public Iterable<PaymentType> getAllPaymentType() {
      return paymentTypeService.getAll();
   }

   @GetMapping("/paymentType/{id}")
   public PaymentType getPaymentTypeById(@PathVariable("id") UUID id) {
      return paymentTypeService.findById(id);
   }

}
