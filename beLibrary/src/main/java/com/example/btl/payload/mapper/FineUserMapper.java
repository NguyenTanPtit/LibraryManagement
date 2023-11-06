package com.example.btl.payload.mapper;

import com.example.btl.entity.Fine;
import com.example.btl.entity.FineUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FineUserMapper {
    FineUserMapper Mapper = Mappers.getMapper(FineUserMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    FineUserDTO mapToDto(Fine fine);

    Fine mapToFine(FineUserDTO fineUserDTO);

}
