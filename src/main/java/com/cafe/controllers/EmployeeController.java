package com.cafe.controllers;

import java.security.Principal;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.EmployeeDto;
import com.cafe.dto.ResponseDto;
import com.cafe.entities.Employee;
import com.cafe.services.EmployeeService;
import com.cafe.utils.ErrorParsingUtility;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

   @Autowired
   private EmployeeService employeeService;

   @PostMapping
   public ResponseEntity<ResponseDto<Employee>> createEmployee(@Valid @RequestBody EmployeeDto employeeDto,
         Errors errors) {
      ResponseDto<Employee> responseDto = new ResponseDto<>();

      if (errors.hasErrors()) {
         responseDto.setMessages(ErrorParsingUtility.parse(errors));
         responseDto.setData(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      }
      return employeeService.save(employeeDto, "lutfi");
   }

   @GetMapping
   public Iterable<Employee> getAllEmployee(Authentication auth) {
      return employeeService.getAll();
   }

   @GetMapping("/{id}")
   public Employee getEmployee(@PathVariable("id") UUID id) {
      System.out.println(id);
      return employeeService.getOne(id);
   }

   @PutMapping("/{id}")
   public ResponseEntity<ResponseDto<Employee>> editEmployee(@Valid @RequestBody EmployeeDto employeeDto,
         @PathVariable("id") UUID id, Errors errors) {
      ResponseDto<Employee> responseDto = new ResponseDto<>();

      if (errors.hasErrors()) {
         responseDto.setMessages(ErrorParsingUtility.parse(errors));
         responseDto.setData(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      }
      return employeeService.edit(employeeDto, id);
   }

   @DeleteMapping("/{id}")
   public void deleteEmployee(@PathVariable("id") UUID id) {
      employeeService.deleteOne(id);
   }

}
