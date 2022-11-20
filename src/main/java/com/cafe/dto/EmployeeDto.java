package com.cafe.dto;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.cafe.entities.JobPosition;
import com.cafe.entities.LastEducation;
import com.cafe.entities.auditor.Auditor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends Auditor {

   private UUID id;

   @NotEmpty(message = "Full name is required")
   private String fullName;

   private String gender;

   private Date dateOfBirth;

   private String placeOfBirth;

   @NotEmpty(message = "Email is required")
   @Email(message = "Invalid email address")
   private String email;

   @NotEmpty(message = "Mobile phone number is required")
   @Pattern(regexp = "[0-9]+", message = "Number is not valid")
   private String mobilePhoneNumber;

   private LastEducation lastEducation;

   private JobPosition jobPosition;

}
