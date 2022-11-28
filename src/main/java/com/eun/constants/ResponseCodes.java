package com.eun.constants;

public enum ResponseCodes {
    SUCCESS( "0000", "성공"),
    ERROR("9999", "프로세싱중 에러가 발생하였습니다."),
    REQUIRED_PARAMETERS("0400","필수값을 확인 하세요."),
    BOARD_NULL("0100","게시글이 없습니다."),
    BOARD_PRIEVATE("0101","게시글이 비밀글입니다.");
    private final String code;
    private final String message;

    ResponseCodes(String code, String message) {
        this.code       = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
