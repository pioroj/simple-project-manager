package com.github.pioroj.spm.domain.exception;

public class InvalidEntityException extends DomainException{

    public InvalidEntityException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
