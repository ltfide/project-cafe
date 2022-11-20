package com.cafe.dto;

import javax.validation.constraints.NotEmpty;

import com.cafe.entities.Employee;
import com.cafe.entities.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

   @NotEmpty(message = "Code is required")
   private String code;

   private double total;

   private double subTotal;

   private double tax;

   private Employee cashier;

   private PaymentType paymentType;

}
