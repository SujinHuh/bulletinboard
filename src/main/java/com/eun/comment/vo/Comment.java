package com.eun.comment.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {

    private int seq;
    private String text;
    private int bbsSeq;
    private int memberSeq;
    private String deleteYn;
    private String createDate;
    private String updateDate;
}
