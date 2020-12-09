package com.github.pioroj.spm.api.error;

import com.github.pioroj.spm.domain.exception.DomainException;
import com.github.pioroj.spm.domain.exception.EntityAlreadyExistsException;
import com.github.pioroj.spm.domain.exception.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleEntityAlreadyExistsException(EntityAlreadyExistsException exception, HttpServletRequest request) {
        return handleDomainException(exception, request, UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorMessage> handleInvalidEntityException(InvalidEntityException exception, HttpServletRequest request) {
        return handleDomainException(exception, request, UNPROCESSABLE_ENTITY);
    }

    private ResponseEntity<ErrorMessage> handleDomainException(DomainException exception, HttpServletRequest request, HttpStatus status) {
        log.warn(String.format("Request: %s, URI: %s, status: %s, code: %s, message: %s",
                request.getMethod(),
                request.getRequestURI(),
                status.value(),
                exception.getErrorCode(),
                exception.getMessage()));
        return status(status)
                .body(new ErrorMessage(exception.getErrorCode(), exception.getMessage()));
    }

}
