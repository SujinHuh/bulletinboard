package com.eun.board.mapper;

import com.eun.board.vo.Bbs;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("Insert into bbs(bbs_cd,title,content,name,email,member_seq,private_yn)" +
            "values(#{bbsCd},#{title}, #{content}, #{name}, #{email}, #{memberSeq}, #{privateYn})")
    @Options(useGeneratedKeys = true, keyProperty = "seq")
    int add(Bbs param);

    @Select("Select seq, title, name, cnt, create_date FROM bbs Where bbs_cd = 'FR' AND delete_yn = 'N' order by seq DESC")
    List<Bbs> getList();


    // seq 일치하는
    @Select("SELECT seq, member_seq, title, content, private_yn, delete_yn FROM bbs where seq = #{seq} AND delete_yn = 'N' ")
    Bbs getView(String seq);


    @Update("UPDATE bbs set title = #{title}, content = #{content}, update_date = now() " +
            "where seq = #{seq} AND member_seq =#{memberSeq}" )
    int modify(Bbs param);

    @Update("UPDATE bbs SET delete_yn = 'Y', update_date = now() WHERE seq = #{seq}")
    int delete(int seq);
}
