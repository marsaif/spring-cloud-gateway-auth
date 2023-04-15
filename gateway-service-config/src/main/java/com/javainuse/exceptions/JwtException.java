package com.javainuse.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseStatus(FORBIDDEN)
public class JwtException  extends RuntimeException {
    public JwtException(String exception) {
        super(exception);
    }
}
