package com.example.btl.controller;

import com.example.btl.entity.Author;
import com.example.btl.entity.Book;
import com.example.btl.entity.BookDto;
import com.example.btl.entity.Categories;
import com.example.btl.payload.mapper.BookMapper;
import com.example.btl.payload.request.CreateBookRequest;
import com.example.btl.payload.response.CreateBookResponse;
import com.example.btl.payload.response.GetBookResponse;
import com.example.btl.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


    @RestController
    @RequestMapping("/api/bookController")
    @RequiredArgsConstructor
    public class BookController {
        private final BookService service;

        @PostMapping("/addBook")
        public ResponseEntity<CreateBookResponse> addBook(
                @RequestBody CreateBookRequest request
        ) {
            return ResponseEntity.ok(service.addBook(request));
        }
        @PostMapping("/updateBook")
        public ResponseEntity<CreateBookResponse> authenticate(
                @RequestParam  String title,
                @RequestParam Author author,
                @RequestParam String pageNumber,
                @RequestParam String price,
                @RequestParam Categories category
        ) {
            var response = service.update(title,author,price,category,pageNumber);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        }

        @PostMapping("/getAllByCategory")
        public ResponseEntity<GetBookResponse> getAllByCategory(
                @RequestParam Long id
        ) {
            List<Book> list = service.getAllByCategory(id);
            List<BookDto> listBookDto = new LinkedList<>();
            list.forEach(item -> listBookDto.add(BookMapper.Mapper.mapToDto(item)));
            GetBookResponse getBookResponse = GetBookResponse.builder()
                    .message("thanh cong")
                    .data(listBookDto)
                    .build();
            return new ResponseEntity<>(getBookResponse, HttpStatusCode.valueOf(200));
//            return service.getAllByCategory(id);
        }
    }

