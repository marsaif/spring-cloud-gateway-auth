package com.example.spring_app.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity, String attribute) {
        super(entity.concat(" ").concat(attribute).concat(" not found!"));
    }
}
