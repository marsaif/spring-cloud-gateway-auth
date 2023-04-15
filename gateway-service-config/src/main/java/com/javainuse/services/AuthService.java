package com.javainuse.services;

import com.javainuse.entities.User;
import com.javainuse.models.UserDto;

public interface AuthService {

    public User register(UserDto userDto) ;

    public String login(UserDto userDto) ;
}
