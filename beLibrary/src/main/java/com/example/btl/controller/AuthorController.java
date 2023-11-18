package com.example.btl.controller;

import com.example.btl.entity.Author;
import com.example.btl.entity.AuthorDTO;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addAuthor")
    public EntityResponse addAuthor(@RequestParam String name) {
        try {
            Author data = Author.builder().name(name).build();
            Author newAuthor = authorService.addAuthor(data);
            AuthorDTO res = AuthorDTO.builder()
                    .id(newAuthor.getId())
                    .name(newAuthor.getName())
                    .build();
            return EntityResponse.builder()
                    .message("Add author successfully!")
                    .data(res)
                    .build();
        }catch (Exception e){
            return EntityResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/updateAuthor")
    public EntityResponse updateAuthor(@RequestBody Author author) {
        try {
            Author data = authorService.update(author);
            AuthorDTO res = AuthorDTO.builder()
                    .id(data.getId())
                    .name(data.getName())
                    .build();
            return EntityResponse.builder()
                    .message("Update author successfully!")
                    .data(res)
                    .build();
        }catch (Exception e){
            return EntityResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/deleteAuthor")
    public EntityResponse deleteAuthor(@RequestParam Long id) {
        try {
            authorService.deleteAuthor(id);
            return EntityResponse.builder()
                    .message("Delete author successfully!")
                    .data(null)
                    .build();
        }catch (Exception e){
            return EntityResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
        }
    }
}
