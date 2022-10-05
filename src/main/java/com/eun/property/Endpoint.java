package com.eun.property;

import org.springframework.lang.NonNull;

import java.util.Arrays;

public class Endpoint {

    // common
    public static final String ROOT = "/";
    public static final String ERROR = "/error";
    public static final String HEALTH = "/health";

    // member
    public static final String LOGIN = "/member/login";
    public static final String LOGIN_PROC = "/member/login/proc";
    public static final String LOGOUT = "/member/logout";
    public static final String MEMBER_CREATE = "/member/create";
    public static final String MEMBER_UPDATE = "/member/update";

    // parsing
    public static final String KAKAO_PARSING = "/parsing/kakaoMap";

    public static final String REDIRECT = "redirect:";
    /**
     * Redirect URI
     * @param args Redirect 하고자 하는 URI
     * @return redirect uri
     */
    public static String redirect(@NonNull final String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REDIRECT);
        Arrays.stream(args).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
