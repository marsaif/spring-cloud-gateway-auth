package com.example.spring_app.repositories;

import com.example.spring_app.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author,String> {
    Page<Author> findAll(Pageable pageable);

}