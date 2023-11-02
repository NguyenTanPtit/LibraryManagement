package com.example.btl.payload.response;

import com.example.btl.entity.Categories;
import com.example.btl.entity.CategoryDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoriesResponse {
    private Object data;
    private String message;

}
