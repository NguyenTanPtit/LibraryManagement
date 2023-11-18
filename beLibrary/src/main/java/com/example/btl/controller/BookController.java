package com.example.btl.controller;

import com.example.btl.entity.Author;
import com.example.btl.entity.Book;
import com.example.btl.entity.BookDto;
import com.example.btl.entity.Categories;
import com.example.btl.payload.mapper.BookMapper;
import com.example.btl.payload.request.CreateBookRequest;
import com.example.btl.payload.response.CreateBookResponse;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.payload.response.GetBookResponse;
import com.example.btl.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
        public ResponseEntity<CreateBookResponse> updateBook(
                @RequestBody Book book
        ) {
            var response = service.update(book);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        }

        @PostMapping("/getAllByCategory")
        public ResponseEntity<GetBookResponse> getAllByCategory(
                @RequestParam Long id
        ) {
            try {
                List<Book> list = service.getAllByCategory(id);
                List<BookDto> listBookDto = new LinkedList<>();
                list.forEach(item -> listBookDto.add(BookMapper.Mapper.mapToDto(item)));
                GetBookResponse getBookResponse = GetBookResponse.builder()
                        .message(null)
                        .data(listBookDto)
                        .build();
                return new ResponseEntity<>(getBookResponse, HttpStatusCode.valueOf(200));
            }catch (Exception e){
                GetBookResponse getBookResponse = GetBookResponse.builder()
                        .message("Some thing wrong! Please try again later!")
                        .data(null)
                        .build();
                return new ResponseEntity<>(getBookResponse, HttpStatusCode.valueOf(500));
            }
        }

        @PostMapping("/getAll")
        public EntityResponse getAll() {
            try {
                List<Book> list = service.getAllBooks();
                List<BookDto> listBookDto = new LinkedList<>();
                list.forEach(item -> listBookDto.add(BookMapper.Mapper.mapToDto(item)));
                return   EntityResponse.builder()
                        .message(null)
                        .data(listBookDto)
                        .build();
            }catch (Exception e){
                return EntityResponse.builder()
                        .message("Some thing wrong! Please try again later!")
                        .data(null)
                        .build();
            }
        }

        @PostMapping("/deleteById")
        public EntityResponse deleteById(
                @RequestParam Integer id
        ) {
            try {
                service.deleteById(id);
                return EntityResponse.builder()
                        .message("Delete success!")
                        .data(null)
                        .build();
            }catch (Exception e){
                return EntityResponse.builder()
                        .message("Some thing wrong! Please try again later!")
                        .data(null)
                        .build();
            }
        }

        @GetMapping("/getBookById")
        public EntityResponse getBookById(@RequestParam Integer id) {
            try {
                Book book = service.getBookById(id);
                BookDto bookDto = BookMapper.Mapper.mapToDto(book);
                return EntityResponse.builder()
                        .message(null)
                        .data(bookDto)
                        .build();
            }catch (Exception e){
                return EntityResponse.builder()
                        .message("Some thing wrong! Please try again later!")
                        .data(null)
                        .build();
            }
        }

    }

