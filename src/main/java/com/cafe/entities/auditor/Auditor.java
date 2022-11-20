package com.cafe.entities.auditor;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.cafe.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Auditor {
   @CreatedBy
   @ManyToOne
   protected User createdBy;

   @LastModifiedBy
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
   protected User updatedBy;

   @CreatedDate
   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;

   @LastModifiedDate
   @Column(name = "updated_at")
   private LocalDateTime updatedAt;
}
