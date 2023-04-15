package com.example.spring_app.services;

import com.example.spring_app.entities.Author;
import com.example.spring_app.models.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    public Author addAuthor(AuthorDto authorDto);

    public Author updateAuthor(String id, AuthorDto authorDto);

    public void deleteAuthor(String id);

    public Author getAuthorById(String id);

    Page<Author> findAll(Pageable pageable);
}
