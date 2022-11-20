package com.cafe.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

   public Optional<User> findByLoginName(String loginName);

   public Optional<User> findByEmail(String email);

   void deleteByLoginName(String loginName);

   public boolean existsUserByLoginName(String loginName);

   public boolean existsUserByEmail(String email);

}
