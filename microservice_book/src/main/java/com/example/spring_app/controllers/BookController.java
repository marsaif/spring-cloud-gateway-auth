package com.example.spring_app.controllers;

import com.example.spring_app.entities.Author;
import com.example.spring_app.entities.Book;
import com.example.spring_app.models.BookDto;
import com.example.spring_app.models.apiResponse.ApiResponse;
import com.example.spring_app.services.BookService;
import com.example.spring_app.services.microservices.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/books")
@Tag(name = "Book Management System", description = "Operations pertaining to Book management")
public class BookController {

    private final BookService bookService;
    Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all Books")
    @GetMapping("/")
    public ResponseEntity<ApiResponse<Page<Book>>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(new ApiResponse(bookService.findAll(pageable)));
    }

    @Operation(summary = "Get a Books by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getUserById(@PathVariable String id) {
        logger.info("Fetching Book with id {}", id);
        Book book = this.bookService.getBookById(id);
        logger.info("Fetched Book with id {}: {}", id, book);
        return ResponseEntity.ok().body(new ApiResponse(book));
    }

    @Operation(summary = "Add Book")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<Book>> addBook(@Valid @RequestBody BookDto bookDto) {
        logger.info("Adding Book {}", bookDto);
        Book book = this.bookService.addBook(bookDto);
        logger.info("Added Book {}", book);
        return ResponseEntity.ok().body(new ApiResponse(book));
    }

    @Operation(summary = "Delete Book")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String id) {
        logger.warn("Deleting Book with id {}", id);
        this.bookService.deleteBook(id);
        logger.warn("Deleted Book with id {}", id);
        return ResponseEntity.ok().body(new ApiResponse("Deleted"));
    }

    @Operation(summary = "Update Book")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateUser(@PathVariable String id, @RequestBody @Valid BookDto bookDto) {
        logger.info("Updating Book with id {} and data {}", id, bookDto);
        Book book = this.bookService.updateBook(id, bookDto);
        logger.info("Updated Book with id {}: {}", id, book);
        return ResponseEntity.ok().body(new ApiResponse(book));
    }


}
