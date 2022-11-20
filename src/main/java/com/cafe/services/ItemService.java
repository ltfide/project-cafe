package com.cafe.services;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafe.dto.ItemDto;
import com.cafe.dto.ResponseDto;
import com.cafe.entities.Item;
import com.cafe.entities.User;
import com.cafe.repositories.ItemRepository;
import com.cafe.utils.UserAuthenticated;

@Service
@Transactional
public class ItemService {

   @Autowired
   private ItemRepository itemRepository;

   @Autowired
   private UserService userService;

   @Autowired
   private ModelMapper modelMapper;

   public ResponseEntity<ResponseDto<Item>> save(ItemDto itemDto) {
      ResponseDto<Item> responseDto = new ResponseDto<>();
      User user = userService.findByLoginName(UserAuthenticated.loginName());

      Item item = modelMapper.map(itemDto, Item.class);
      item.setCreatedAt(LocalDateTime.now());
      item.setUpdatedAt(LocalDateTime.now());
      item.setCreatedBy(user);
      item.setUpdatedBy(user);
      responseDto.getMessages().add("Item created successfully");
      responseDto.setData(itemRepository.save(item));
      return ResponseEntity.ok(responseDto);
   }

   public Iterable<Item> getAll() {
      return itemRepository.findAll();
   }

   public Item getOne(UUID id) {
      Optional<Item> item = itemRepository.findById(id);

      if (!item.isPresent()) {
         return null;
      }

      return item.get();
   }

   public ResponseEntity<ResponseDto<Item>> edit(ItemDto itemDto, UUID id) {
      ResponseDto<Item> responseDto = new ResponseDto<>();
      Optional<Item> employeeData = itemRepository.findById(id);

      if (!employeeData.isPresent()) {
         responseDto.getMessages().add("Item not found");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      }

      Item item = modelMapper.map(itemDto, Item.class);
      item.setId(id);
      item.setCreatedAt(employeeData.get().getCreatedAt());
      item.setUpdatedAt(LocalDateTime.now());
      item.setCreatedBy(employeeData.get().getCreatedBy());
      item.setUpdatedBy(userService.findByLoginName(UserAuthenticated.loginName()));
      responseDto.getMessages().add("Item edited successfully");
      responseDto.setData(itemRepository.save(item));
      return ResponseEntity.ok(responseDto);
   }

   public ResponseEntity<Object> deleteOne(UUID id) {
      Optional<Item> item = itemRepository.findById(id);

      if (!item.isPresent()) {
         return ResponseEntity.badRequest().body(Map.of("message", "Item not found"));
      }

      itemRepository.deleteById(id);
      return ResponseEntity.ok(Map.of("message", "Item deleted successfully"));
   }

}
