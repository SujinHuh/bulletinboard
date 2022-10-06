package com.eun.common.handler;

import com.eun.common.exception.BusinessException;
import com.eun.common.property.Endpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String handleBusinessException(BusinessException e, RedirectAttributes redirectAttributes) {
        log.error("BusinessException ", e);
        redirectAttributes.addFlashAttribute("status", e.getHttpStatus().name());
        redirectAttributes.addFlashAttribute("statusCode", e.getHttpStatus().value());
        return Endpoint.redirect(Endpoint.ERROR);
    }
}
