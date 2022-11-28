package com.eun.common.exception;

import com.eun.constants.ResponseCodes;
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

    private final String message;
    private final String errorCode;

    public BusinessException(final ResponseCodes codes) {
        super(codes.getCode() + codes.getMessage());
        this.errorCode = codes.getCode();
        this.message = codes.getMessage();
    }

}
