package com.cafe.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.cafe.entities.auditor.Auditor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Item extends Auditor implements Serializable {

   @Id
   @GeneratedValue
   @Column(name = "id")
   @Type(type = "org.hibernate.type.UUIDCharType")
   private UUID id;

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "description", columnDefinition = "TEXT")
   private String description;

   @Column(name = "price")
   private Double price;

}
