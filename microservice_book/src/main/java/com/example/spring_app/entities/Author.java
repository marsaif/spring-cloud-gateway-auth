package com.example.spring_app.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private String id;
    private String name;
    private String email;

}