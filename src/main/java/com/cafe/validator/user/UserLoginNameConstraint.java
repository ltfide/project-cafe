package com.cafe.validator.user;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserLoginNameUniqueValidator.class)
public @interface UserLoginNameConstraint {

   String message();

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};

}
