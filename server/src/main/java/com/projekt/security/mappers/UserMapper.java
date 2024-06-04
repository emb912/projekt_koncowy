package com.projekt.security.mappers;


import com.projekt.security.entities.User;
import com.projekt.security.models.SignUpDto;
import com.projekt.security.models.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}