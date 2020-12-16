package com.github.rojekp.spm.api.error;

import com.github.rojekp.spm.domain.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private ErrorCode errorCode;
    private String message;
}
