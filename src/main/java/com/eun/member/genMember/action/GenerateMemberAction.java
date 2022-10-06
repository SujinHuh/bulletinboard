package com.eun.member.genMember.action;

import com.eun.constants.ResponseCodes;
import com.eun.constants.ResponseVo;
import com.eun.member.genMember.vo.MemberVo;
import com.eun.common.property.Endpoint;
import com.eun.common.property.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class GenerateMemberAction {

//    @Autowired
//    AuthenticationManager authenticationManager;

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

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword());

//        Authentication authentication = authenticationManager.authenticate(token);


        ResponseVo response = new ResponseVo();
        log.info("login!!");

        log.info("param >>> {}", param.toString());

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
