package com.example.btl.payload.mapper;

import com.example.btl.entity.BorrowQueueDetail;
import com.example.btl.entity.BorrowQueueDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QueueBorrowDetailMapper {
    QueueBorrowDetailMapper MAPPER = Mappers.getMapper(QueueBorrowDetailMapper.class);
    @Mapping(source = "user.id", target = "userId")
    BorrowQueueDetailDto mapToBorrowQueueDetailDto(BorrowQueueDetail borrowQueueDetail);

}
