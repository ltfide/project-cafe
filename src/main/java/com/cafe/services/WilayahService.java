package com.cafe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.cafe.entities.wilayah.City;
import com.cafe.entities.wilayah.District;
import com.cafe.entities.wilayah.Province;
import com.cafe.entities.wilayah.Village;

@Service
public class WilayahService {

   @Value("${wilayah.url}")
   private String hostname;

   @Value("${wilayah.province}")
   private String province;

   @Value("${wilayah.city}")
   private String city;

   @Value("${wilayah.district}")
   private String district;

   @Value("${wilayah.village}")
   private String village;

   @Autowired
   private RestTemplate restTemplate;

   public ResponseEntity<?> getAllProvince() {
      try {
         ResponseEntity<Province[]> response = restTemplate
               .getForEntity(hostname + province, Province[].class);
         return response;
      } catch (ResourceAccessException | HttpClientErrorException e) {
         return ResponseEntity.notFound().build();
      }
   }

   public ResponseEntity<?> getCity(int provinceId) {
      try {
         ResponseEntity<City[]> response = restTemplate
               .getForEntity(hostname + city + provinceId + ".json", City[].class);
         return response;
      } catch (ResourceAccessException | HttpClientErrorException e) {
         return ResponseEntity.notFound().build();
      }
   }

   public ResponseEntity<?> getDistrict(int regencyId) {
      try {
         ResponseEntity<District[]> response = restTemplate
               .getForEntity(hostname + district + regencyId + ".json", District[].class);
         return response;
      } catch (ResourceAccessException | HttpClientErrorException e) {
         return ResponseEntity.notFound().build();
      }
   }

   public ResponseEntity<?> getVillage(int districtId) {
      try {
         ResponseEntity<Village[]> response = restTemplate
               .getForEntity(hostname + village + districtId + ".json", Village[].class);
         return response;
      } catch (ResourceAccessException | HttpClientErrorException e) {
         return ResponseEntity.notFound().build();
      }
   }

}
