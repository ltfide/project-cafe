package com.cafe.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.cafe.entities.auditor.Timestamps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Timestamps<LocalDateTime> {

   @Id
   @GeneratedValue
   @Column(name = "id")
   @Type(type = "org.hibernate.type.UUIDCharType")
   private UUID id;

   @Column(name = "full_name", length = 100, nullable = false)
   private String fullName;

   @Column(name = "email", length = 100, nullable = false, unique = true)
   private String email;

   @Column(name = "mobile_phone_number", length = 20, nullable = false)
   private String mobilePhoneNumber;

   @Column(name = "work_phone_number", length = 20, nullable = false)
   private String workPhoneNumber;

   @Column(name = "login_name", length = 50, nullable = false, unique = true)
   private String loginName;

   @Column(name = "password", nullable = false)
   private String password;

   @Column(name = "address", columnDefinition = "TEXT")
   private String address;

}
