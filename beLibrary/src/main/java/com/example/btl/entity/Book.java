package com.example.btl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Book {
    @Id
    private int id;

    private String title;

    private String author;
    private String pageNumber;
    private String price;
    private String category;

}
