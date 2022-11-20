package com.cafe.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cafe.entities.User;
import com.cafe.services.UserService;

public class AuditorAwareImpl implements AuditorAware<String> {

   @Autowired
   private UserService userService;

   @Override
   public Optional<String> getCurrentAuditor() {
      String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
      User user = userService.findByLoginName(loginName);
      // User user = new User();
      // user.setLoginName("Lutfi");
      // user.setEmail("lutfi@gmail.com");
      // user.setFullName("Lutfi Dendiansyah");
      // user.setMobilePhoneNumber("08193818302");

      // return Optional.of(user.get());
      // return Optional.of(String.valueOf(user.get().getId()));
      return null;
   }

}
