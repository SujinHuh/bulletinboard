package com.eun.common.utill;


public class StringUtils {

    
    /* 공백포함 문자열 검사 */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || !containsText(str);
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
