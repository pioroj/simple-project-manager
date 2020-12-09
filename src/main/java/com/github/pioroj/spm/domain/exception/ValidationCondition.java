package com.github.pioroj.spm.domain.exception;

public class ValidationCondition {

    private boolean condition;

    private ValidationCondition(boolean condition) {
        this.condition = condition;
    }

    public static ValidationCondition when(boolean condition) {
        return new ValidationCondition(condition);
    }

    public void thenInvalidEntity(ErrorCode errorCode, String message) {
        if (condition) {
            throw new InvalidEntityException(message, errorCode);
        }
    }

    public void thenEntityAlreadyExists(ErrorCode errorCode, String message) {
        if (condition) {
            throw new EntityAlreadyExistsException(message, errorCode);
        }
    }

}
