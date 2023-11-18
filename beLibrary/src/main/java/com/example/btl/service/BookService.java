package com.example.btl.service;

import com.example.btl.entity.*;
import com.example.btl.payload.request.CreateBookRequest;
import com.example.btl.payload.request.RegisterRequest;
import com.example.btl.payload.response.AuthenticationResponse;
import com.example.btl.payload.response.CreateBookResponse;
import com.example.btl.payload.response.GetBookResponse;
import com.example.btl.repository.BookRepository;
import com.example.btl.repository.QueueRepository;
import com.example.btl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final QueueRepository queueRepository;
    public CreateBookResponse addBook(CreateBookRequest request) {
        var book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .pageNumber(request.getPageNumber())
                .categories(request.getCategory())
                .price(request.getPrice())
                .state(request.getState())
                .image(request.getImage())
                .description(request.getDescription())
                .build();
        Book b = bookRepository.save(book);
        QueueBorrow q = new QueueBorrow();
        q.setBook(b);
        queueRepository.save(q);
        return CreateBookResponse.builder()
                .message("Add book success!")
                .build();
    }

    public CreateBookResponse update(Book book) {
        bookRepository.save(book);
        return CreateBookResponse.builder()
                .message("Update book success!")
                .build();
    }

    public List<Book> getAllByCategory(Long category) {
        return bookRepository.getAllByCategories_Id(category);
    }
    public List<Book> getAllByAuthorId(Long id) {
        List<Book> data = bookRepository.getAllByAuthorId(id);
        return data;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void updateState(Long id, String state) {
        Book book = bookRepository.findById(id.intValue()).get();
        book.setState(state);
        bookRepository.save(book);
    }
}
