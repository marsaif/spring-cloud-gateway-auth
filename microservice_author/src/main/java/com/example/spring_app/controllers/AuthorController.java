package com.example.spring_app.controllers;

import com.example.spring_app.entities.Author;
import com.example.spring_app.models.AuthorDto;
import com.example.spring_app.models.apiResponse.ApiResponse;
import com.example.spring_app.services.AuthorService;
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
@RequestMapping("/authors")
@Tag(name = "Author Management System", description = "Operations pertaining to Author management")
public class AuthorController {

    private final AuthorService authorService;
    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Get all Authors")
    @GetMapping("/")
    public ResponseEntity<ApiResponse<Page<Author>>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(new ApiResponse(authorService.findAll(pageable)));
    }

    @Operation(summary = "Get a Author by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> getAuthorById(@PathVariable String id) {
        logger.info("Fetching Author with id {}", id);
        Author author = this.authorService.getAuthorById(id);
        logger.info("Fetched Author with id {}: {}", id, author);
        return ResponseEntity.ok().body(new ApiResponse(author));
    }

    @Operation(summary = "Add Author")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<Author>> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        logger.info("Adding Author {}", authorDto);
        Author author = this.authorService.addAuthor(authorDto);
        logger.info("Added Author {}", author);
        return ResponseEntity.ok().body(new ApiResponse(author));
    }

    @Operation(summary = "Delete Author")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAuthor(@PathVariable String id) {
        logger.warn("Deleting Author with id {}", id);
        this.authorService.deleteAuthor(id);
        logger.warn("Deleted Author with id {}", id);
        return ResponseEntity.ok().body(new ApiResponse("Deleted"));
    }

    @Operation(summary = "Update Author")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> updateAuthor(@PathVariable String id, @RequestBody @Valid AuthorDto authorDto) {
        logger.info("Updating Author with id {} and data {}", id, authorDto);
        Author author = this.authorService.updateAuthor(id, authorDto);
        logger.info("Updated Author with id {}: {}", id, author);
        return ResponseEntity.ok().body(new ApiResponse(author));
    }


}
