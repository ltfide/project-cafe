package com.cafe.dto.auth;

import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto<T> {
   private List<String> messages = new ArrayList<>();
   private T user;
   private String token;
}
