package com.eun.todo.mapper;

import com.eun.todo.vo.Todo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TodoMapper {

    @Insert("INSERT INTO TODO(email, text) values(#{email}, #{text})")
    int add(Todo param);

    @Select("SELECT * from TODO WHERE email = #{email} and delete_yn = 'N' order by create_date desc")
    List<Todo> list(String email);

}