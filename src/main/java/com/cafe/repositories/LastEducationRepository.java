package com.cafe.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entities.LastEducation;

@Repository
public interface LastEducationRepository extends JpaRepository<LastEducation, UUID> {

}
