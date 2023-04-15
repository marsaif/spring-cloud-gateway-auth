package com.example.spring_app.services.impl;

import com.example.spring_app.config.MapperConfig;
import com.example.spring_app.entities.Author;
import com.example.spring_app.exceptions.NotFoundException;
import com.example.spring_app.models.AuthorDto;
import com.example.spring_app.repositories.AuthorRepository;
import com.example.spring_app.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AuthorServiceImpl implements AuthorService {

    private final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private final AuthorRepository authorRepository;
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author addAuthor(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String id) {
        logger.debug("Deleting Author with id {}", id);
        authorRepository.deleteById(id);
        logger.debug("Deleted Author with id {}", id);
    }

    @Override
    public Author getAuthorById(String id) {
        logger.debug("Fetching Author with id {}", id);
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author", "Id"));
        System.out.println(author);
        logger.debug("Fetched Author with id {}: {}", id, author);
        return author;
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }


    @Override
    public Author updateAuthor(String id, AuthorDto authorDto) {
        logger.debug("Updating Author with id {} and data {}", id, authorDto);
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author", "Id"));
        author.setEmail(authorDto.getEmail());
        logger.debug("Updated Author with id {} and data {}", id, authorDto);
        return authorRepository.save(author);
    }


}
