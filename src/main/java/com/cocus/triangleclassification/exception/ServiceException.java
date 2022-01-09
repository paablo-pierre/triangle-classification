package com.cocus.triangleclassification.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ServiceException extends BaseException implements Serializable {

    public ServiceException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
