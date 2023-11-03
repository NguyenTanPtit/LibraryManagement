package com.example.btl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String title;
    private String description;
    private String image;
    @JsonIgnore
    private Author author;
    private String authorName;
    private Long authorId;
    private String pageNumber;
    private String price;
    @JsonIgnore
    private Categories categories;
    private String categoryName;
    private Long categoryId;

}
