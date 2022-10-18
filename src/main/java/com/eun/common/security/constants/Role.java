package com.eun.common.security.constants;

public enum Role {

    ROLE_ADMIN("00", "ROLE_ADMIN"),
    ROLE_PROFESSOR("01", "ROLE_PROFESSOR"),
    ROLE_STUDENT("02", "ROLE_STUDENT");

    private String code;
    private String codeNm;

    Role(String code, String codeNm){
        this.code = code;
        this.codeNm = codeNm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

}
