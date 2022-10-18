package com.eun.common.security.services;

import com.eun.common.exception.BusinessException;
import com.eun.member.mapper.MemberMapper;
import com.eun.member.vo.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // username == email WebSecurityConfiguration에서 설정 매핑되어있음.
        Member member = memberMapper.getEmail(email);

        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(member.getRole()));

        return UserDetail
                .builder()
                .member(member)
                .build();
    }
}
