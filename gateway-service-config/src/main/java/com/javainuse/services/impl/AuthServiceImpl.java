package com.javainuse.services.impl;

import com.javainuse.entities.Role;
import com.javainuse.entities.User;
import com.javainuse.exceptions.IncorrectPasswordException;
import com.javainuse.exceptions.NotFoundException;
import com.javainuse.models.apiRequest.UserDto;
import com.javainuse.repositories.UserRepository;
import com.javainuse.security.jwt.JwtService;
import com.javainuse.services.AuthService;
import com.javainuse.services.mappers.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService ;
    private final UserRepository userRepository ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder ;

    public AuthServiceImpl(JwtService jwtService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(UserDto userDto) {
        User user = UserMapper.mapBookDtoToUser(userDto);
        user.setRole(Role.USER);
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public String login(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow(() -> new NotFoundException("User", "Username"));
        String encodedPassword = user.getPassword() ;
        Boolean matched = bCryptPasswordEncoder.matches(userDto.getPassword(),encodedPassword);
        if (!matched) {
            throw new IncorrectPasswordException("Incorrect Password");
        }
        String jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }
}
