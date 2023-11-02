package com.example.btl.payload.mapper;

import com.example.btl.entity.Book;
import com.example.btl.entity.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper Mapper = Mappers.getMapper(BookMapper.class);
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "categories.id", target = "categoryId")
    BookDto mapToDto(Book book);
    Book mapToBook(BookDto bookDto);

}
