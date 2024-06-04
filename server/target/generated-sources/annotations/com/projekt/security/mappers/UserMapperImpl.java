package com.projekt.security.mappers;

import com.projekt.security.entities.User;
import com.projekt.security.models.SignUpDto;
import com.projekt.security.models.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-02T15:23:53+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.3 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.login( user.getLogin() );
        userDto.role( user.getRole() );

        return userDto.build();
    }

    @Override
    public User signUpToUser(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.login( signUpDto.getLogin() );

        return user.build();
    }
}
