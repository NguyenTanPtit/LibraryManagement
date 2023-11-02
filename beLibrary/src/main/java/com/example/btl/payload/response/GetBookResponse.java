package com.example.btl.payload.response;

import com.example.btl.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBookResponse {
   private String message;
    private Object data;
}
