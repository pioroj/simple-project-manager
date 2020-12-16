package com.github.rojekp.spm.domain.exception;

public abstract class DomainException extends RuntimeException {

    private ErrorCode errorCode;

    public DomainException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
