package com.example.spring_app.services.microservices;

import com.example.spring_app.entities.Author;
import com.example.spring_app.models.apiResponse.ApiResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "author-service", url = "https://localhost:8000/authors/")
public interface AuthorService {
    @GetMapping("/{id}")
    ApiResponse<Author> getAuthorInformation(@PathVariable String id);
}
