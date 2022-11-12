package com.eun.board.mapper;

import com.eun.board.vo.Bbs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("Insert into bbs(bbs_cd,title,content,name,email,member_seq,private_yn)" +
            "values(#{bbsCd},#{title}, #{content}, #{name}, #{email}, #{memberSeq}, #{privateYn})")
    int add(Bbs param);

}
