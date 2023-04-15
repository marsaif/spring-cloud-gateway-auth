package com.javainuse.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseStatus(FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String exception) {
        super(exception);
    }
}
