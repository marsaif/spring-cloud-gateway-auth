package com.example.spring_app.services.impl;

import com.example.spring_app.config.MapperConfig;
import com.example.spring_app.entities.Author;
import com.example.spring_app.entities.Book;
import com.example.spring_app.exceptions.NotFoundException;
import com.example.spring_app.models.BookDto;
import com.example.spring_app.models.apiResponse.ApiResponse;
import com.example.spring_app.repositories.BookRepository;
import com.example.spring_app.services.BookService;
import com.example.spring_app.services.mappers.BookMapper;
import com.example.spring_app.services.microservices.AuthorService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class BookServiceImpl implements BookService {

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;
    private final AuthorService authorService ;

    public BookServiceImpl(BookRepository bookRepository,AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService ;
    }

    @Override
    public Book addBook(BookDto bookDto) {
        Book book = BookMapper.mapBookDtoToBook(bookDto);

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(String id, BookDto bookDto) {
        return null;
    }

    @Override
    public void deleteBook(String id) {
        logger.debug("Deleting Book with id {}", id);
        bookRepository.deleteById(id);
        logger.debug("Deleted Book with id {}", id);
    }

    @Override
    public Book getBookById(String id) {
        logger.debug("Fetching Book with id {}", id);
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Author", "Id"));
        System.out.println(book.getAuthor().getId());
        ApiResponse<Author> apiResponse = authorService.getAuthorInformation(book.getAuthor().getId());
        book.setAuthor(apiResponse.getData());
        logger.debug("Fetched Book with id {}: {}", id, book);
        return book;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }




}
