package com.eun.common.security.services;

import com.eun.common.security.vo.User;
import com.eun.member.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername!!! ::: " + username);
        User user = new User();

        Map<String, Object> result = memberMapper.findByEmail(username);
        if(result == null){
            new UsernameNotFoundException("User Not Found with username: " + username);
        }

        user.setEmail((String) result.get("email"));
        user.setPassword((String) result.get("password"));
        user.setUsername((String) result.get("username"));
        return UserDetailsImpl.build(user);
    }

}
