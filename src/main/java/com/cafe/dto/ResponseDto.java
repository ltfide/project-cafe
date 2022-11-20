package com.cafe.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ResponseDto<T> {
   private List<String> messages = new ArrayList<>();
   private T data;
}
