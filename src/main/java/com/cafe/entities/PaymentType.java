package com.cafe.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentType {

   @Id
   @GeneratedValue
   @Column(name = "id")
   @Type(type = "org.hibernate.type.UUIDCharType")
   private UUID id;

   @Column(name = "name", length = 100, nullable = false)
   private String name;

   @Column(name = "code", length = 100, nullable = false, unique = true)
   private String code;

   @CreatedDate
   @Column(name = "created_at", nullable = false, updatable = false)
   private LocalDateTime createdAt;

}
