package com.javainuse.Controllers;

import com.javainuse.entities.User;
import com.javainuse.models.UserDto;
import com.javainuse.models.apiResponse.ApiResponse;
import com.javainuse.services.AuthService;
import com.javainuse.services.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<UserDto>> register(@Valid @RequestBody UserDto userDto) {
        User user = authService.register(userDto) ;
        return ResponseEntity.ok(new ApiResponse(user));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody UserDto userDto) {
        String token = authService.login(userDto) ;
        return ResponseEntity.ok(new ApiResponse(token));
    }

}
