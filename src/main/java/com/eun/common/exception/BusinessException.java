package com.eun.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * BusinessException
 * 프로젝트 내 최상위 Exception 입니다. 프로젝트 내 비지니스 코드 관련된 exception 발생시 해당 클래스를 사용 하거나,
 * 해당 클래스를 상속받아 사용하여야 합니다.
 *
 */
@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
    private final String errorCode;

    public BusinessException(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCode = null;
    }

    public BusinessException(final String message, final String code, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCode = code;
    }
}
