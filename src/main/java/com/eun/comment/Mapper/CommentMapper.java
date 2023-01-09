package com.eun.comment.Mapper;

import com.eun.comment.vo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO COMMENT(bbs_seq, member_seq, text) values( #{bbsSeq},#{memberSeq}, #{text})")
    @Options(useGeneratedKeys = true, keyProperty = "seq")
    int add(Comment param);

    @Select("SELECT seq, text, member_seq, create_date FROM COMMENT WHERE bbs_seq = ${seq} and delete_yn = 'N'")
    List<Comment> list(String seq);

}
