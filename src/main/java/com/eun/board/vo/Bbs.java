package com.eun.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class Bbs {
    private int seq;//seq
    private String bbsCd; //bbs_code
    private String title;//제목
    @NotNull(message = "게시글을 작성해주세요!")
    private String content; //내용
    private String name;//이름
    private String email;       // 등록자 이메일 or user seq
    private int memberSeq; //member시퀀스
    private int cnt;// 조회수
    private String privateYn;// 비밀여부
    private String deleteYn; //삭제여부
    private String createDate;  // 생성일자
    private String updateDate;  // 수정일자
    private boolean isMyBbs; //게시글 소유여부
}
