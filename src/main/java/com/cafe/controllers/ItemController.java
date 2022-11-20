package com.cafe.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.ItemDto;
import com.cafe.dto.ResponseDto;
import com.cafe.entities.Item;
import com.cafe.services.ItemService;
import com.cafe.utils.ErrorParsingUtility;

@RestController
@RequestMapping("/api/item")
public class ItemController {

   @Autowired
   private ItemService itemService;

   @PostMapping
   public ResponseEntity<ResponseDto<Item>> createItem(@Valid @RequestBody ItemDto itemDto, Errors errors) {
      ResponseDto<Item> responseDto = new ResponseDto<>();

      if (errors.hasErrors()) {
         responseDto.setMessages(ErrorParsingUtility.parse(errors));
         responseDto.setData(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      }
      return itemService.save(itemDto);
   }

   @GetMapping
   public Iterable<Item> getAllItem() {
      return itemService.getAll();
   }

   @GetMapping("/{id}")
   public Item getOneItem(@PathVariable("id") UUID id) {
      return itemService.getOne(id);
   }

   @PutMapping("/{id}")
   public ResponseEntity<ResponseDto<Item>> editEmployee(@Valid @RequestBody ItemDto itemDto,
         @PathVariable("id") UUID id, Errors errors) {
      ResponseDto<Item> responseDto = new ResponseDto<>();

      if (errors.hasErrors()) {
         responseDto.setMessages(ErrorParsingUtility.parse(errors));
         responseDto.setData(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      }
      return itemService.edit(itemDto, id);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Object> deleteItem(@PathVariable("id") UUID id) {
      return itemService.deleteOne(id);
   }

}
