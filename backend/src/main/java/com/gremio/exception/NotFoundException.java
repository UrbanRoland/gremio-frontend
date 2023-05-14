package com.gremio.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1184963802610034035L;
    public NotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}