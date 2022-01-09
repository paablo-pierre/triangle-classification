package com.cocus.triangleclassification.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class BaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 2595762020732775112L;

    private final String errorCode;
    private final String errorMessage;

    public BaseException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
