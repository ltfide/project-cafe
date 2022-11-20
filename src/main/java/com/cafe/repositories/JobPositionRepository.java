package com.cafe.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.entities.JobPosition;

public interface JobPositionRepository extends JpaRepository<JobPosition, UUID> {

}
