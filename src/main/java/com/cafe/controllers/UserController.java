package com.cafe.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.ResponseDto;
import com.cafe.dto.UserDto;
import com.cafe.dto.auth.AuthResponseDto;
import com.cafe.entities.User;
import com.cafe.services.UserService;
import com.cafe.utils.ErrorParsingUtility;
import com.cafe.utils.TokenUtil;
import com.cafe.utils.UserAuthenticated;

@RestController
@RequestMapping("/api/user")
public class UserController {

   @Autowired
   private UserService userService;

   @GetMapping("/test")
   public String getAll() {
      return "Get All";
   }

   @GetMapping
   public User getUserDetail() {
      User response = userService.findByLoginName(UserAuthenticated.loginName());
      return response;
   }

   @PostMapping
   public ResponseEntity<AuthResponseDto<User>> registerUser(@Valid @RequestBody UserDto request, Errors errors) {
      AuthResponseDto<User> authResponse = new AuthResponseDto<>();

      if (errors.hasErrors()) {
         authResponse.setMessages(ErrorParsingUtility.parse(errors));
         authResponse.setUser(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
      }
      return userService.register(request);
   }

   @PutMapping
   public ResponseEntity<AuthResponseDto<User>> editUser(@Valid @RequestBody UserDto request, Errors errors) {
      AuthResponseDto<User> authResponse = new AuthResponseDto<>();

      if (errors.hasErrors()) {
         authResponse.setMessages(ErrorParsingUtility.parse(errors));
         authResponse.setUser(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
      }
      return userService.edit(request);
   }

   @DeleteMapping
   public void deleteUser(Authentication authentication) {
      userService.delete(authentication.getName());
   }

}
