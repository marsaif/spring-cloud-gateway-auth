package com.javainuse.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ResponseStatus(UNAUTHORIZED)
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String message) {
        super(message);
    }

}