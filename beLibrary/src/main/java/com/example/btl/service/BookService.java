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
                .build();
        Book b = bookRepository.save(book);
        QueueBorrow q = new QueueBorrow();
        q.setBook(b);
        queueRepository.save(q);
        return CreateBookResponse.builder()
                .message("thanh cong")
                .build();
    }

    public CreateBookResponse update(String title, Author author, String price, Categories category, String pageNumber) {
        var book = Book.builder()
                .title(title)
                .author(author)
                .pageNumber(pageNumber)
                .categories(category)
                .price(price)
                .build();
        bookRepository.save(book);
        return CreateBookResponse.builder()
                .message("thanh cong")
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
}
