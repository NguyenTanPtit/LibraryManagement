package com.example.btl.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Book {
    @Id
    private Integer id;
    private String title;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String pageNumber;
    private String price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;
    private String state;
}
