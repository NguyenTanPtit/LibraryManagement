package com.example.btl.controller;

import com.example.btl.payload.request.CreateBookRequest;
import com.example.btl.payload.response.CreateBookResponse;
import com.example.btl.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



    @Controller
    @RequestMapping(value="/api/bookController")
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
                @RequestParam  String author,
                @RequestParam String pageNumber,
                @RequestParam String price,
                @RequestParam String category
        ) {
            var response = service.update(title,author,price,pageNumber,category);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        }
    }

