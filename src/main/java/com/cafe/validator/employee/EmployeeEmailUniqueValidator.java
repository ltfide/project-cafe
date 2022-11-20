package com.cafe.validator.employee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cafe.repositories.EmployeeRepository;
import com.cafe.services.EmployeeService;

public class EmployeeEmailUniqueValidator implements ConstraintValidator<EmployeeEmailUniqueConstraint, String> {

   @Autowired
   private EmployeeService employeeService;

   @Override
   public boolean isValid(String emailEmployee, ConstraintValidatorContext arg1) {
      return employeeService.isEmailValid(emailEmployee);
      // return true;
   }

}
