package com.example.btl.payload.mapper;

import com.example.btl.entity.QueueBorrow;
import com.example.btl.entity.QueueBorrowDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QueueBorrowMapper {
    QueueBorrowMapper MAPPER = Mappers.getMapper(QueueBorrowMapper.class);
    @Mapping(source = "bookId", target = "book.id")
    QueueBorrow mapToQueueBorrow(QueueBorrowDto queueBorrowDto);
    @Mapping(source = "book.id", target = "bookId")
    QueueBorrowDto mapToQueueBorrowDto(QueueBorrow queueBorrow);
}
