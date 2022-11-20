package com.cafe.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "order_item")
public class OrderItem {
   
   @Id
   @GeneratedValue
   @Column(name = "id")
   @Type(type="org.hibernate.type.UUIDCharType")
   private UUID id;

   @ManyToOne
   private Order order;

   @ManyToOne
   private Item item;

   @Column(name = "quantity")
   private Integer quantity;

   @Column(name = "price_total")
   private Double priceTotal;

   @Column(name = "note", columnDefinition = "TEXT")
   private String note;

}
