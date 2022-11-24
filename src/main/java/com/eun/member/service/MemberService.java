package com.eun.member.service;

import com.eun.constants.ResponseCodes;
import com.eun.member.mapper.MemberMapper;
import com.eun.member.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class MemberService {

    @Autowired MemberMapper memberMapper;

    public int create(Member param){
        log.info("create >>> " +  param.toString());


        Member member = memberMapper.getEmail(param.getEmail());

        log.info("create >>> " + member);


        param.setRole("ROLE_STUDENT");
        return memberMapper.create(param);
    }

    public Member getEmail(String email){
        return memberMapper.getEmail(email);
    }

}
