package com.example.eun.property;

import org.springframework.lang.NonNull;

import java.util.Arrays;

public class Endpoint {

    // common
    public static final String ROOT = "/";
    public static final String ERROR = "/error";
    public static final String HEALTH = "/health";

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
