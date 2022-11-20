package com.cafe.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.cafe.validator.user.UserEmailUniqueConstraint;
import com.cafe.validator.user.UserLoginNameConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

   @NotEmpty(message = "Login name is required")
   @UserLoginNameConstraint(message = "Login name is already exists")
   private String loginName;

   @NotEmpty(message = "Password is required")
   private String password;

   @NotEmpty(message = "Full name is required")
   private String fullName;

   @NotEmpty(message = "Email is required")
   @Email(message = "Invalid email address")
   @UserEmailUniqueConstraint(message = "Email is already exists")
   private String email;

   @NotEmpty(message = "Mobile phone number is required")
   @Pattern(regexp = "[0-9]+", message = "Number is not valid")
   private String mobilePhoneNumber;

   private String address;

   @NotEmpty(message = "Mobile phone number is required")
   private String workPhoneNumber;

   private LocalDateTime createdAt;

   private LocalDateTime updatedAt;

}
