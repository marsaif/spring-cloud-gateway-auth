package com.javainuse.services.mappers;

import com.javainuse.entities.User;
import com.javainuse.models.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class UserMapper {

    static ModelMapper modelMapper = new ModelMapper();

    public static User mapBookDtoToUser(UserDto userDto) {
        TypeMap<UserDto, User> userMapper = modelMapper.getTypeMap(UserDto.class, User.class);
        if (userMapper == null) {
            TypeMap<UserDto, User> userDtoUserTypeMap = modelMapper.createTypeMap(UserDto.class, User.class);
        }
        return modelMapper.map(userDto, User.class);
    }
}
