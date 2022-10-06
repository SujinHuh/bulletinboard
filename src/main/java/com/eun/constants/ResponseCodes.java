package com.eun.constants;

public enum ResponseCodes {
    SUCCESS(
            "0000",
            "성공",
            "성공"
    ),
    ERROR(
            "9999",
            "프로세싱중 에러가 발생하였습니다.",
            "프로세싱중 에러가 발생하였습니다."
    ),
    REQUIRED_PARAMETERS(
            "0428",
            "필수값을 확인 하세요.",
            "Precondition Required"
    ),
    MISSING_DATA(
            "9198",
            "데이터가 누락 되었습니다.",
            "데이터 누락"
    ),
    PARSING(
            "9199",
            "데이터 처리중 오류가 발생하였습니다.",
            "데이터 파싱 에러"
    );

    private final String code;
    private final String message;
    private final String devMessage;   // 클라이언트 전달 메시지가 아닌 프로그램상 디버깅을 위한 메시지임.

    ResponseCodes(String code, String message, String devMessage) {
        this.code       = code;
        this.message = message;
        this.devMessage = devMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public String getMessage() {
        return message;
    }
}
