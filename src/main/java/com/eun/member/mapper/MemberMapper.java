package com.eun.member.mapper;

import com.eun.member.vo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO MEMBER(email, password, name, role) values(#{email}, #{password}, #{name}, #{role})")
    int create(Member param);

    @Select("SELECT * FROM MEMBER WHERE EMAIL = #{email}")
    Member getEmail(String email);

}