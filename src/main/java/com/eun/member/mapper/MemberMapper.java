package com.eun.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM users1 where email = #{email}")
    Map<String, Object> findByEmail(String email);

}