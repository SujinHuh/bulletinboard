package com.eun.common.handler;

import com.eun.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * CustomExceptionHandler
 * 프로젝트 내 Exception 발생시 handler
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    /**
     * BusinessException
     * 프로젝트 내에서 직접 구현했을 경우 발생되는 공통 exception
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException e, RedirectAttributes redirectAttributes) {
        Map<String, Object> result = new HashMap<>();

        result.put("code", e.getErrorCode());
        result.put("message", e.getMessage());

        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e) {

        log.info("handleNoHandlerFoundException!!!!!!!!!!!!!!!");
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
