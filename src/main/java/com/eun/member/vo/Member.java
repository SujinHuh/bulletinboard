package com.eun.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class Member {

    private int seq;
    private String role;
    private String name;
    private String email;
    private String password;
    private String createDate;
    private String updateDate;

}
