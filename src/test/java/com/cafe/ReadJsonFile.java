package com.cafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJsonFile {

   private static final ObjectMapper objectMapper = new ObjectMapper();

   public static List<?> getItemFromJson() throws IOException {
      InputStream inputStream = new FileInputStream(new File("src/main/resources/banks.json"));
      TypeReference<List<?>> typeReference = new TypeReference<List<?>>() {
      };
      return objectMapper.readValue(inputStream, typeReference);
   }

   @Test
   void testName() throws IOException {
      System.out.println(objectMapper.writeValueAsString(getItemFromJson()));
   }

}
