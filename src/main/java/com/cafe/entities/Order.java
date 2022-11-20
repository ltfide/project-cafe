package com.cafe.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

   @Id
   @GeneratedValue
   @Column(name = "id")
   @Type(type = "org.hibernate.type.UUIDCharType")
   private UUID id;

   @Column(name = "code", nullable = false)
   private String code;

   @ManyToOne
   private Employee cashier;

   @Column(name = "total")
   private Double total;

   @Column(name = "sub_total")
   private Double subTotal;

   @Column(name = "tax")
   private Double tax;

   @ManyToOne
   private PaymentType paymentType;

   @CreatedDate
   @Column(name = "created_at", nullable = false, updatable = false)
   private LocalDateTime createdAt;

}
