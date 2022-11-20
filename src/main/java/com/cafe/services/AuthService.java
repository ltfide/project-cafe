package com.cafe.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import com.cafe.dto.auth.AuthResponseDto;
import com.cafe.dto.auth.LoginRequest;
import com.cafe.entities.User;
import com.cafe.repositories.UserRepository;
import com.cafe.utils.TokenUtil;

@Service
@Transactional
public class AuthService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   BCryptPasswordEncoder bCryptPasswordEncoder;

   public ResponseEntity<AuthResponseDto<User>> login(LoginRequest request) {
      AuthResponseDto<User> authResponse = new AuthResponseDto<>();
      User user = findByLoginName(request.getLoginName());

      if (user == null) {
         authResponse.getMessages().add("Loginname or password is incorrect");
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
      }
      try {
         // Validate username/password with DB(required in case of Stateless
         // Authentication)
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               request.getLoginName(), request.getPassword()));

         authResponse.getMessages().add("Login Successfully");
         authResponse.setUser(user);
         authResponse.setToken(tokenUtil.generateToken(user.getLoginName()));
         return ResponseEntity.ok(authResponse);
      } catch (AuthenticationException | UnexpectedRollbackException e) {
         authResponse.getMessages().add("Loginname or password is incorrect");
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
      }
   }

   public User findByLoginName(String loginName) {
      Optional<User> response = userRepository.findByLoginName(loginName);

      if (!response.isPresent()) {
         return null;
      }

      return response.get();
   }

}
