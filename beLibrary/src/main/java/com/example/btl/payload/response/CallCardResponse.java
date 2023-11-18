package com.example.btl.payload.response;

import com.example.btl.entity.BookDto;
import com.example.btl.entity.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallCardResponse {
    private Long id;
    private String borrowDate;
    private String dueDate;
    private String state;
    private String note;
    private UserDTO user;
    private List<BookDto> books;
}
