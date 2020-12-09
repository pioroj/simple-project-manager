package com.github.pioroj.spm.api.error;

import com.github.pioroj.spm.domain.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private ErrorCode errorCode;
    private String message;
}
