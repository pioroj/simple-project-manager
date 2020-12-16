package com.github.rojekp.spm.domain.exception;

public class EntityAlreadyExistsException extends DomainException {

    public EntityAlreadyExistsException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
