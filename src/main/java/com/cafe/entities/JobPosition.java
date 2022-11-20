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
@Table(name = "job_position")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPosition {

   @Id
   @GeneratedValue
   @Column(name = "id")
   @Type(type="org.hibernate.type.UUIDCharType")
   private UUID id;

   @Column(name = "title", nullable = false)
   private String title;

   @Column(name = "salary")
   private Double salary;

   @CreatedDate
   @Column(name = "created_at", nullable = false, updatable = false)
   private LocalDateTime createdAt;

}
