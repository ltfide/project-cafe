package com.cafe.validator.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cafe.services.UserService;

public class UserEmailUniqueValidator implements ConstraintValidator<UserEmailUniqueConstraint, String> {

   @Autowired
   private UserService userService;

   @Override
   public boolean isValid(String userEmail, ConstraintValidatorContext arg1) {
      return !userService.isEmailValid(userEmail);
   }

}
