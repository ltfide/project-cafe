package com.cafe.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

   public Optional<Employee> findByEmail(String email);

   public boolean existsEmployeeByEmail(String email);

}
