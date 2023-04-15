package com.example.spring_app.services;

import com.example.spring_app.entities.Book;
import com.example.spring_app.models.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    public Book addBook(BookDto bookDto);

    public Book updateBook(String id, BookDto bookDto);

    public void deleteBook(String id);

    public Book getBookById(String id);

    Page<Book> findAll(Pageable pageable);
}
