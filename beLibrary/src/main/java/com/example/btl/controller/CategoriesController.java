package com.example.btl.controller;

import com.example.btl.entity.Book;
import com.example.btl.entity.Categories;
import com.example.btl.entity.CategoryDTO;
import com.example.btl.payload.response.CategoriesResponse;
import com.example.btl.payload.response.GetBookResponse;
import com.example.btl.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    @PostMapping("/getAll")
    public CategoriesResponse getAllCategories() {
        try {
            List<Categories> data = categoriesService.getALlCategories();
            List<CategoryDTO> list = new ArrayList<>();
            data.forEach(item -> list.add(CategoryDTO.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .build()));
            return CategoriesResponse.builder()
                    .message(null)
                    .data(list)
                    .build();
        }catch (Exception e){
            return CategoriesResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
        }

    }

    @PostMapping("/getBooksByCategoriesId")
    public GetBookResponse getBooksByCategoriesId(
            @RequestParam Long id) {
        List<Book> data = categoriesService.getBooksByCategoriesId(id);
        return GetBookResponse.builder()
                .message("thanh cong")
                .data(data)
                .build();
    }

    @PostMapping("/create")
    public CategoriesResponse createCategory(@RequestParam String name) {
        try {
            Categories data =categoriesService.createCategory(name);
            return CategoriesResponse.builder()
                    .message("Create category successfully!")
                    .data(data)
                    .build();
        }catch (Exception e){
            return CategoriesResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/updateCategory")
    public CategoriesResponse updateCategory(@RequestBody Categories category) {
        try {
            Categories data = categoriesService.updateCategory(category);
            return CategoriesResponse.builder()
                    .message("Update category successfully!")
                    .data(data)
                    .build();
        }catch (Exception e){
            return CategoriesResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/deleteCategory")
    public CategoriesResponse deleteCategory(@RequestParam Long id) {
        try {
            categoriesService.deleteCategory(id);
            return CategoriesResponse.builder()
                    .message("Delete category successfully!")
                     .data(null)
                    .build();
        }catch (Exception e){
            return CategoriesResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
        }
    }
}
