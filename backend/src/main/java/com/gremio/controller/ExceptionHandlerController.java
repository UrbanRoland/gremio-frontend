package com.gremio.controller;

import com.gremio.exception.NotFoundException;
import com.gremio.model.dto.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    /**
     * Handles the {@link NotFoundException} and returns an appropriate HTTP response.
     *
     * @param exception The NotFoundException instance.
     * @return A ResponseEntity with HTTP status 404 (Not Found) and an ExceptionResponse body.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(final NotFoundException exception) {
        log.info("Not found exception: {}", exception.getMessage());
        return createExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    /**
     * Creates an ExceptionResponse entity with the specified HTTP status and error message.
     *
     * @param status  The HTTP status to set in the response entity.
     * @param message The error message to include in the ExceptionResponse body.
     * @return A ResponseEntity with the specified HTTP status and an ExceptionResponse body.
     */
    private ResponseEntity<ExceptionResponse> createExceptionResponse(final HttpStatus status, final String message) {
        return ResponseEntity.status(status).body(new ExceptionResponse(message));
    }

}