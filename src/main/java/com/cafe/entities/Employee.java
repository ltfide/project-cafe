package com.cafe.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import com.cafe.entities.auditor.Auditor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE employees SET is_active = false WHERE id=?")
public class Employee extends Auditor implements Serializable {

   @Id
   @GeneratedValue
   @Column(name = "id", nullable = false)
   @Type(type = "org.hibernate.type.UUIDCharType")
   private UUID id;

   @Column(name = "full_name", length = 100, nullable = false)
   private String fullName;

   @Column(name = "gender", length = 20, nullable = true)
   private String gender;

   @Column(name = "dob", length = 20, nullable = true)
   private Date dateOfBirth;

   @Column(name = "pob", length = 50, nullable = true)
   private String placeOfBirth;

   @Column(name = "email", length = 50, nullable = false, unique = true)
   private String email;

   @Column(name = "mobile_phone_number", length = 20, nullable = false)
   private String mobilePhoneNumber;

   @ManyToOne
   private LastEducation lastEducation;

   @ManyToOne
   private JobPosition jobPosition;

   @Column(name = "is_active")
   private boolean isActive = Boolean.TRUE;

}
