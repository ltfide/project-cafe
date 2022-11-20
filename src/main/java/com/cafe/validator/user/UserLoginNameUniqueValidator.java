package com.cafe.validator.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cafe.repositories.UserRepository;
import com.cafe.services.UserService;

public class UserLoginNameUniqueValidator implements ConstraintValidator<UserLoginNameConstraint, String> {

   @Autowired
   private UserService userService;

   @Override
   public boolean isValid(String loginName, ConstraintValidatorContext arg1) {
      return !userService.isLoginNameValid(loginName);
   }

}
