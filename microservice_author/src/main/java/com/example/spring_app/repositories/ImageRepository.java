package com.example.spring_app.repositories;

import com.example.spring_app.entities.Author;
import com.example.spring_app.entities.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends MongoRepository<Image,String> {
    Page<Image> findAll(Pageable pageable);
    Optional<Image> findByName(String name);

}
