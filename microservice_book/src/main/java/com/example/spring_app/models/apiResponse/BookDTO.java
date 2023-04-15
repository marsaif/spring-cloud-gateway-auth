package com.example.spring_app.models.apiResponse;


import com.example.spring_app.entities.Author;
import com.example.spring_app.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private Author author;



}
