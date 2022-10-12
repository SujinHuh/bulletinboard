package com.eun.member.action;

import com.eun.common.property.Endpoint;
import com.eun.common.property.Template;
import com.eun.common.security.jwt.JwtUtils;
import com.eun.common.security.services.UserDetailsImpl;
import com.eun.constants.ResponseCodes;
import com.eun.constants.ResponseVo;
import com.eun.member.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class MemberAction {

    @Autowired JwtUtils jwtUtils;
    @Autowired AuthenticationManager authenticationManager;


    // 페이지 이동
    @GetMapping(value = Endpoint.LOGIN)
    public String login() {return Template.LOGIN;
    }

    @GetMapping(value = Endpoint.MEMBER_CREATE)
    public String create() {
        return Template.MEMBER_CREATE;
    }

    @GetMapping(value = Endpoint.MEMBER_UPDATE)
    public String update() {
        return Template.MEMBER_UPDATE;
    }

    // api
    @PostMapping(value = Endpoint.LOGIN)
    @ResponseBody public ResponseVo login(@RequestBody MemberVo param) {
        ResponseVo response = new ResponseVo();
        log.info("login!!");
        log.info("email ::: " + param.getEmail());
        log.info("password ::: " + param.getPassword());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(param.getEmail(), param.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        log.info("jwt ::: " + jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        System.out.printf("roles >>" + roles.toString());

//        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);


        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessages(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

    @PostMapping(value = Endpoint.MEMBER_CREATE)
    @ResponseBody public ResponseVo create(@RequestBody MemberVo param) {
        ResponseVo response = new ResponseVo();
        log.info("create");
        return response;
    }

    @PostMapping(value = Endpoint.MEMBER_UPDATE)
    @ResponseBody public ResponseVo update(@RequestBody MemberVo param) {
        ResponseVo response = new ResponseVo();
        log.info("update");
        return response;
    }

    @PostMapping(value = Endpoint.LOGOUT)
    @ResponseBody public ResponseVo logout(@RequestBody MemberVo param) {
        ResponseVo response = new ResponseVo();
        return response;
    }

}
