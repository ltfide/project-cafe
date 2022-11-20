package com.cafe.dto;

import javax.validation.constraints.NotEmpty;

import com.cafe.entities.auditor.Auditor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto extends Auditor {

   @NotEmpty(message = "Name is required")
   private String name;

   private String description;

   private Double price;

}
