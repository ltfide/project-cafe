package com.cafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.auth.LoginRequest;
import com.cafe.dto.auth.AuthResponseDto;
import com.cafe.entities.User;
import com.cafe.services.AuthService;
import com.cafe.utils.ErrorParsingUtility;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   private AuthService authService;

   @PostMapping("/login")
   public ResponseEntity<AuthResponseDto<User>> login(@RequestBody LoginRequest request, Errors errors) {
      AuthResponseDto<User> authResponse = new AuthResponseDto<>();

      if (errors.hasErrors()) {
         authResponse.setMessages(ErrorParsingUtility.parse(errors));
         authResponse.setUser(null);
         authResponse.setToken(null);
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
      }
      return authService.login(request);
   }

}
