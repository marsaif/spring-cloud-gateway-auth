package com.example.spring_app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    @NotBlank(message = "name cannot be blank")
    @NotNull
    private String name;
    @NotBlank(message = "email cannot be blank")
    @NotNull
    @Email(message = "Email should be valid")
    private String email;

}
