package com.example.btl.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDTO {
    private Long id;
    private String name;
}
