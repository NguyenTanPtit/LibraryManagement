package com.example.btl.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author") // mappedBy: đánh dấu thuộc tính nào là khóa ngoại
    private List<Book> books;   // 1 tác giả có nhiều sách
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
