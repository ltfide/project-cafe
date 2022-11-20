package com.cafe.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafe.dto.UserDto;
import com.cafe.dto.auth.AuthResponseDto;
import com.cafe.entities.User;
import com.cafe.repositories.UserRepository;
import com.cafe.utils.TokenUtil;
import com.cafe.utils.UserAuthenticated;

@Service
@Transactional
public class UserService implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   public ResponseEntity<AuthResponseDto<User>> register(UserDto request) {
      AuthResponseDto<User> authResponse = new AuthResponseDto<>();

      // if (userRepository.existsUserByEmail(request.getEmail())) {
      // authResponse.getMessages().add("Email is already exists");
      // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
      // }

      // if (userRepository.existsUserByLoginName(request.getLoginName())) {
      // authResponse.getMessages().add("Login name is already exists");
      // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
      // }

      User user = modelMapper.map(request, User.class);

      String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      user.setCreatedAt(LocalDateTime.now());
      user.setUpdatedAt(LocalDateTime.now());

      authResponse.getMessages().add("Employee created successfully");
      authResponse.setUser(userRepository.save(user));
      authResponse.setToken(tokenUtil.generateToken(user.getLoginName()));
      return ResponseEntity.ok(authResponse);
   }

   public User findByLoginName(String loginName) {
      return userRepository.findByLoginName(loginName).orElse(null);
   }

   public ResponseEntity<AuthResponseDto<User>> edit(UserDto userDto) {
      AuthResponseDto<User> authResponse = new AuthResponseDto<>();
      Optional<User> userData = userRepository.findByLoginName(UserAuthenticated.loginName());

      if (!userData.isPresent()) {
         authResponse.getMessages().add("User not found");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
      }

      // if (!userData.get().getEmail().equals(userDto.getEmail())
      // && userRepository.existsUserByEmail(userDto.getEmail())) {
      // authResponse.getMessages().add("Email is already exists");
      // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
      // }

      // if (!userData.get().getLoginName().equals(userDto.getLoginName())
      // && userRepository.existsUserByLoginName(userDto.getLoginName())) {
      // authResponse.getMessages().add("Login name is already exists");
      // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
      // }

      if (userData.get().getId() != null) {
         User user = modelMapper.map(userDto, User.class);
         user.setId(userData.get().getId());
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         user.setCreatedAt(userData.get().getCreatedAt());
         user.setUpdatedAt(LocalDateTime.now());
         authResponse.getMessages().add("User edited successfully");
         authResponse.setUser(userRepository.save(user));
      }

      return ResponseEntity.ok(authResponse);
   }

   public void delete(String loginName) {
      userRepository.deleteByLoginName(loginName);
   }

   public boolean isEmailValid(String email) {
      User user = findByLoginName(UserAuthenticated.loginName());
      if (user == null && userRepository.existsUserByEmail(email)) {
         return true;
      }
      try {
         if (!user.getEmail().equals(email)
               && userRepository.existsUserByEmail(email) && user != null) {
            return true;
         }
         return false;
      } catch (NullPointerException e) {
         return false;
      }
   }

   public boolean isLoginNameValid(String loginName) {
      User user = findByLoginName(UserAuthenticated.loginName());
      if (user == null && userRepository.existsUserByLoginName(loginName)) {
         return true;
      }
      try {
         if (!user.getLoginName().equals(loginName)
               && userRepository.existsUserByLoginName(loginName) && user != null) {
            return true;
         }
         return false;
      } catch (NullPointerException e) {
         return false;
      }
   }

   @Override
   public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
      Optional<User> opt = userRepository.findByLoginName(loginName);

      org.springframework.security.core.userdetails.User springUser = null;

      if (opt.isEmpty()) {
         throw new UsernameNotFoundException("User with loginName: " + loginName + " not found");
      } else {
         User user = opt.get(); // retrieving user from DB
         // Set<String> roles = user.getRoles();
         Set<GrantedAuthority> ga = new HashSet<>();
         // for (String role : roles) {
         // ga.add(new SimpleGrantedAuthority(role));
         // }

         springUser = new org.springframework.security.core.userdetails.User(
               loginName,
               user.getPassword(),
               ga);
      }

      return springUser;
   }

}
