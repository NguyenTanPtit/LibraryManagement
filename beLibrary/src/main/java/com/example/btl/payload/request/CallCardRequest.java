package com.example.btl.payload.request;

import com.example.btl.entity.Book;
import com.example.btl.entity.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallCardRequest {
    private Long id;
    private String borrowDate;
    private String dueDate;
    private String state;
    private String note;
    private Long userId;
    private List<BookDto> books;
}
