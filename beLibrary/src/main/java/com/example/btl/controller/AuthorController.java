package com.example.btl.controller;

import com.example.btl.entity.Author;
import com.example.btl.entity.AuthorDTO;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/getAll")
    public EntityResponse getAllAuthors() {
        try {
            List<Author> data = authorService.getAllAuthors();
            List<AuthorDTO> res = new ArrayList<>();
            data.forEach(item -> res.add(AuthorDTO.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .build()));
            return EntityResponse.builder()
                    .message(null)
                    .data(res)
                    .build();
        }catch (Exception e){
            return EntityResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
        }
    }
}
