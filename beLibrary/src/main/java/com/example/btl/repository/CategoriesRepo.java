package com.example.btl.repository;

import com.example.btl.entity.Book;
import com.example.btl.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<Categories, Long> {
    List<Book> findBooksById(Long id);

}
