package com.eun.todo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Todo {

    private int seq;            // seq
    private String text;        // 내용
    private String email;       // 등록자 이메일 or user seq
    private String successYn;   // 성공여부
    private String deleteYn;    // 삭제여부
    private String createDate;  // 생성일자
    private String updateDate;  // 수정일자

}
