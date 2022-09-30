package com.eun.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Locale;


/**
 * MessageHandler
 * Messages.properties 에 정의되어 있는 메세지를 간편하게 핸들링하여 사용하기 위한 핸들러
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final MessageSource messageSource;

    /**
     * Get Message
     * @param messageCode 메세지 코드
     * @param args (있다면) 메세지 코드에 삽입할 arguments
     * @return message
     */
    public String get(@NonNull final String messageCode, final String ... args) {
        return messageSource.getMessage(messageCode, args, Locale.getDefault());
    }
}
