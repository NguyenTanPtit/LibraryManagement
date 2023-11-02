package com.example.btl.service;

import com.example.btl.entity.Book;
import com.example.btl.entity.Categories;
import com.example.btl.payload.response.CategoriesResponse;
import com.example.btl.repository.CategoriesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepo categoriesRepo;

    public List<Categories> getALlCategories() {
        return categoriesRepo.findAll();
    }
    @Query(value = "SELECT * FROM book b WHERE b.categories_id = :id", nativeQuery = true)
    public List<Book> getBooksByCategoriesId(Long id) {
        return categoriesRepo.findBooksById(id);
    }

    public Categories createCategory(String name) {
        Categories category = new Categories();
        category.setName(name);
        return categoriesRepo.save(category);
    }
}
