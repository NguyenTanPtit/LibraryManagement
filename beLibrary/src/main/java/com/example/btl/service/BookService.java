package com.example.btl.service;

import com.example.btl.entity.Book;
import com.example.btl.entity.ERole;
import com.example.btl.entity.User;
import com.example.btl.payload.request.CreateBookRequest;
import com.example.btl.payload.request.RegisterRequest;
import com.example.btl.payload.response.AuthenticationResponse;
import com.example.btl.payload.response.CreateBookResponse;
import com.example.btl.repository.BookRepository;
import com.example.btl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public CreateBookResponse addBook(CreateBookRequest request) {
        var book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .pageNumber(request.getPageNumber())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();
        bookRepository.save(book);
        return CreateBookResponse.builder()
                .message("thanh cong")
                .build();
    }

    public CreateBookResponse update(String title, String author, String price, String category, String pageNumber) {
        var book = Book.builder()
                .title(title)
                .author(author)
                .pageNumber(pageNumber)
                .category(category)
                .price(price)
                .build();
        bookRepository.save(book);
        return CreateBookResponse.builder()
                .message("thanh cong")
                .build();
    }
}
