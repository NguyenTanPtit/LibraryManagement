package com.example.btl.service;

import com.example.btl.entity.Author;
import com.example.btl.repository.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }
    //update author
    public Author update(Author author) {
        return authorRepo.save(author);
    }

    //add author
    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }
    //delete author
    public void deleteAuthor(Long id) {
        authorRepo.deleteById(id);
    }
}
