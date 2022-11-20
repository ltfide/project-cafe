package com.cafe.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cafe.dto.EmployeeDto;
import com.cafe.dto.ResponseDto;
import com.cafe.entities.Employee;
import com.cafe.entities.User;
import com.cafe.repositories.EmployeeRepository;
import com.cafe.utils.UserAuthenticated;

@Service
@Transactional
public class EmployeeService {

   @Autowired
   private EmployeeRepository employeeRepository;

   @Autowired
   private UserService userService;

   @Autowired
   private ModelMapper modelMapper;

   public ResponseEntity<ResponseDto<Employee>> save(EmployeeDto employeeDto, String loginName) {
      ResponseDto<Employee> responseDto = new ResponseDto<>();
      User user = userService.findByLoginName(UserAuthenticated.loginName());

      if (employeeRepository.existsEmployeeByEmail(employeeDto.getEmail())) {
         responseDto.getMessages().add("Email is already exists");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      }

      Employee employee = modelMapper.map(employeeDto, Employee.class);
      employee.setCreatedAt(LocalDateTime.now());
      employee.setUpdatedAt(LocalDateTime.now());
      employee.setCreatedBy(user);
      employee.setUpdatedBy(user);

      responseDto.getMessages().add("Employee created successfully");
      responseDto.setData(employeeRepository.save(employee));
      return ResponseEntity.ok(responseDto);
   }

   public Iterable<Employee> getAll() {
      return employeeRepository.findAll();
   }

   public Employee getOne(UUID id) {
      Optional<Employee> employee = employeeRepository.findById(id);

      if (!employee.isPresent()) {
         return null;
      }

      System.out.println(employee.get());
      return employee.get();
   }

   public ResponseEntity<ResponseDto<Employee>> edit(EmployeeDto employeeDto, UUID id) {
      ResponseDto<Employee> responseDto = new ResponseDto<>();
      Optional<Employee> employeeData = employeeRepository.findById(id);

      if (!employeeData.isPresent()) {
         responseDto.getMessages().add("Employee not found");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      }

      if (!employeeData.get().getEmail().equals(employeeDto.getEmail())
            && employeeRepository.existsEmployeeByEmail(employeeDto.getEmail())) {
         responseDto.getMessages().add("Email is already exists");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      }

      Employee employee = modelMapper.map(employeeDto, Employee.class);
      employee.setId(id);
      employee.setCreatedAt(employeeData.get().getCreatedAt());
      employee.setUpdatedAt(LocalDateTime.now());
      employee.setCreatedBy(employeeData.get().getCreatedBy());
      employee.setUpdatedBy(userService.findByLoginName(UserAuthenticated.loginName()));

      responseDto.getMessages().add("Employee edited successfully");
      responseDto.setData(employeeRepository.save(employee));
      return ResponseEntity.ok(responseDto);
   }

   public boolean isEmailValid(String email) {
      Optional<Employee> isExists = employeeRepository.findByEmail(email);

      if (!isExists.isPresent()) {
         return true;
      }

      if (!isExists.get().getEmail().equals(email)
            && employeeRepository.existsEmployeeByEmail(email)) {
         return false;
      }

      return true;
   }

   public void deleteOne(UUID id) {
      employeeRepository.deleteById(id);
   }

}
