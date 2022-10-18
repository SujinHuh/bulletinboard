package com.eun.member.mapper;

import com.eun.member.vo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO MEMBER(email, password, username, role) values(#{email}, #{encPassword}, #{username}, #{role})")
    int create(Member param);

    @Select("SELECT * FROM MEMBER WHERE EMAIL = #{email}")
    Member getEmail(String email);

}