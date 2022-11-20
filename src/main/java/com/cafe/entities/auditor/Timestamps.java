package com.cafe.entities.auditor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Timestamps<T> {
   @CreatedDate
   @Column(name = "created_at", updatable = false)
   private T createdAt;

   @LastModifiedDate
   @Column(name = "updated_at")
   private T updatedAt;
}
