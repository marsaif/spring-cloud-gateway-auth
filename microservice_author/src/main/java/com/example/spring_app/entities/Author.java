package com.example.spring_app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

@Document("authors")
public class Author {
    @Id
    private String id ;
    private String name;
    private String email;

}
