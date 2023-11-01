package com.amalitech.tradingproject.config;

import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.entity.User;
import com.amalitech.tradingproject.payload.UserPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    UserDto convertToUserDto(User user);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "password",target = "password")
    User convertToUser(UserPayload userPayload);

    void updateUserDetails(@MappingTarget User user, UserPayload userPayload);
}
