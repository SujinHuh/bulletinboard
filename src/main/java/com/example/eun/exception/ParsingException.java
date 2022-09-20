package com.example.eun.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 상위 예외는 모두 응답을 위한 코드 포함으로 신규 생성함
 */
@Getter
public class ParsingException extends RuntimeException {

    public static final String ERROR                                    = "오류";
    public static final String ERROR_MESSAGE_ESSENTIAL_PRECONDITION     = "서비스 실행 요건에 부정합 합니다.";
    public static final String ERROR_MESSAGE_NOT_SUPPORT_FORMAT         = "미지원 형식 입니다.";

    private final HashMap<String, ArrayList<String>> results;

    public ParsingException(final HashMap<String, ArrayList<String>> results) {
        this.results = results;
    }
}
