package com.eun.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

    private String role;
    private String email;
    private String username;
    private String password;
    private String encPassword;

}
