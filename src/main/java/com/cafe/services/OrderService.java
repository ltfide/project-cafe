package com.cafe.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafe.dto.OrderDto;
import com.cafe.dto.ResponseDto;
import com.cafe.entities.Order;
import com.cafe.repositories.OrderRepository;

@Service
@Transactional
public class OrderService {

   @Autowired
   private OrderRepository orderRepository;

   @Autowired
   private ModelMapper modelMapper;

   public ResponseEntity<ResponseDto<Order>> saveOrder(OrderDto orderDto) {
      ResponseDto<Order> responseDto = new ResponseDto<>();

      Order order = modelMapper.map(orderDto, Order.class);
      order.setCreatedAt(LocalDateTime.now());
      responseDto.getMessages().add("Order created successfully");
      responseDto.setData(orderRepository.save(order));
      return ResponseEntity.ok(responseDto);
   }

   public Iterable<Order> findAll(Pageable pageable) {
      return orderRepository.findAll(pageable);
   }

   public Iterable<Order> findByMonth(int month, Pageable pageable) {
      return orderRepository.findByMonth(month, pageable);
   }

}
