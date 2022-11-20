package com.cafe.repositories;

import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cafe.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

   @Query("SELECT o FROM Order o WHERE month(o.createdAt) = :month")
   Page<Order> findByMonth(@PathParam("month") int month, Pageable pageable);

   Page<Order> findAll(Pageable pageable);

}
