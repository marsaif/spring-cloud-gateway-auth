package com.javainuse.config;

import com.javainuse.filters.AuthenticationFilter;
import com.javainuse.repositories.UserRepository;
import com.javainuse.routes.RouteValidator;
import com.javainuse.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    @Bean
    public AuthenticationFilter authenticationFilter(RouteValidator validator, JwtService jwtService, UserRepository userRepository) {
        return new AuthenticationFilter(validator, jwtService, userRepository);
    }

}
