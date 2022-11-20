package com.cafe.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.OrderDto;
import com.cafe.dto.ResponseDto;
import com.cafe.entities.Order;
import com.cafe.services.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

   @Autowired
   private OrderService orderService;

   @PostMapping
   public ResponseEntity<ResponseDto<Order>> createOrder(@Valid @RequestBody OrderDto orderDto, Errors errors) {
      ResponseDto<Order> responseDto = new ResponseDto<>();

      if (errors.hasErrors()) {
         for (ObjectError error : errors.getAllErrors()) {
            responseDto.getMessages().add(error.getDefaultMessage());
         }
         responseDto.setData(null);
         return ResponseEntity.badRequest().body(responseDto);
      }
      return orderService.saveOrder(orderDto);
   }

   @GetMapping
   public Iterable<Order> getByMonth(@RequestParam(defaultValue = "1") int page,
         @RequestParam(required = false, defaultValue = "0") int month) {
      Pageable pageable = PageRequest.of(page - 1, 10);

      if (month == 0) {
         return orderService.findAll(pageable);
      }
      return orderService.findByMonth(month, pageable);
   }

}
