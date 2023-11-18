package com.example.btl.payload.request;

import com.example.btl.entity.Author;
import com.example.btl.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private Author author;
    private String pageNumber;
    private String price;
    private Categories category;
    private String image;
    private String state;
    private String description;
}
