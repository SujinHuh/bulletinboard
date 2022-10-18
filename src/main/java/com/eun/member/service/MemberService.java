package com.eun.member.service;

import com.eun.member.mapper.MemberMapper;
import com.eun.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired MemberMapper memberMapper;

    public int create(Member param){
        param.setRole("ROLE_STUDENT");
        return memberMapper.create(param);
    }

    public Member getEmail(String email){
        return memberMapper.getEmail(email);
    }

}
