package com.cafe.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobPositionDto {

   @NotEmpty(message = "Title is required")
   private String title;

   @NotEmpty(message = "Salary is required")
   private String salary;
}
