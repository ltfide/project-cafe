package com.cafe.entities;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

   @CreatedDate
   @Temporal(TemporalType.TIMESTAMP)
   protected Date createdAt;

   @LastModifiedDate
   @Temporal(TemporalType.TIME)
   protected Date updateAt;

   protected User createdBy;

   protected User updateBy;

}
