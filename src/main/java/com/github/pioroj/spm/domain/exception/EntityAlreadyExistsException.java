package com.github.pioroj.spm.domain.exception;

public class EntityAlreadyExistsException extends DomainException {

    public EntityAlreadyExistsException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
