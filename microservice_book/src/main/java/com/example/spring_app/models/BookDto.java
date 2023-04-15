package com.example.spring_app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    @NotBlank(message = "title cannot be blank")
    @NotNull
    private String title;
    @NotBlank(message = "authorId cannot be blank")
    @NotNull
    private String authorId;

}
