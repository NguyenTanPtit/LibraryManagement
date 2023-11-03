package com.example.btl.payload.mapper;

import com.example.btl.entity.Author;
import com.example.btl.entity.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper Mapper = Mappers.getMapper(AuthorMapper.class);

   AuthorDTO mapToDto(Author author);

    Author mapToAuthor(AuthorDTO authorDTO);
}
