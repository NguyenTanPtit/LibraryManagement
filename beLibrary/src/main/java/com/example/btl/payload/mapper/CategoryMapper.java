package com.example.btl.payload.mapper;

import com.example.btl.entity.Categories;
import com.example.btl.entity.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper Mapper = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO mapToDto(Categories categories);
    Categories mapToCategories(CategoryDTO categoryDTO);
}
