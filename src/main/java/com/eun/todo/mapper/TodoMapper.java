package com.eun.todo.mapper;

import com.eun.todo.vo.Todo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TodoMapper {

    @Insert("INSERT INTO TODO(member_seq, text) values(#{memberSeq}, #{text})")
    int add(Todo param);

    @Select("SELECT seq, text, success_yn from TODO WHERE member_seq = #{seq} AND delete_yn = 'N' ORDER BY create_date DESC")
    List<Todo> list(int seq);

    @Update("UPDATE TODO SET success_yn = 'Y', update_date = CURRENT_TIMESTAMP WHERE seq = #{todoSeq} AND member_seq = #{memberSeq}")
    int success(int todoSeq, int memberSeq);

    @Update("UPDATE TODO SET delete_yn = 'Y', update_date = CURRENT_TIMESTAMP WHERE seq = #{todoSeq} AND member_seq = #{memberSeq}")
    int delete(int todoSeq, int memberSeq);



}