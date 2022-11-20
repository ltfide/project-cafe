package com.cafe.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entities.PaymentType;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, UUID> {

}
