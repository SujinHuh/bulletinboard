package com.eun.board.mapper;

import com.eun.board.vo.Bbs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("Insert into bbs(bbs_cd,title,content,name,email,member_seq,private_yn)" +
            "values(#{bbsCd},#{title}, #{content}, #{name}, #{email}, #{memberSeq}, #{privateYn})")
    int add(Bbs param);

    @Select("Select seq, title, name, cnt, create_date FROM bbs Where bbs_cd = 'FR' AND delete_yn = 'N' order by seq DESC")
    List<Bbs> getList();


    // seq 일치하는
    @Select("SELECT seq, title, content, private_yn, delete_yn FROM bbs where seq = #{seq} AND delete_yn = 'N' ")
    Bbs getView(String seq);
}
