package com.eun.member.action;

import com.eun.common.property.Endpoint;
import com.eun.common.property.Template;
import com.eun.constants.ResponseCodes;
import com.eun.constants.ResponseVo;
import com.eun.member.service.MemberService;
import com.eun.member.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MemberAction {

    @Autowired MemberService memberService;
    @Autowired PasswordEncoder passwordEncoder;

    // 페이지 이동
    @GetMapping(value = Endpoint.LOGIN)
    public String login() {
        return Template.LOGIN;
    }

    @GetMapping(value = Endpoint.MEMBER_CREATE)
    public String create() {
        return Template.MEMBER_CREATE;
    }

    @GetMapping(value = Endpoint.MEMBER_UPDATE)
    public String update() {
        return Template.MEMBER_UPDATE;
    }

    @PostMapping(value = Endpoint.LOGIN_PROCESS)
    @ResponseBody public ResponseVo login(@RequestBody Member param) {
        ResponseVo response = new ResponseVo();
        log.info("login!!");
        log.info("email ::: " + param.getEmail());
        log.info("password ::: " + param.getPassword());

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessages(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

    @PostMapping(value = Endpoint.MEMBER_CREATE)
    @ResponseBody public ResponseVo create(@RequestBody Member param) {
        System.out.printf("create");
        ResponseVo response = new ResponseVo();

        param.setEncPassword(passwordEncoder.encode(param.getPassword()));

        int result = memberService.create(param);

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessages(ResponseCodes.SUCCESS.getMessage());

        if(0 < result){
            response.setData("{result : true}");
        }

        return response;
    }

    @PostMapping(value = Endpoint.MEMBER_UPDATE)
    @ResponseBody public ResponseVo update(@RequestBody Member param) {
        ResponseVo response = new ResponseVo();
        log.info("update");
        return response;
    }

    @PostMapping(value = Endpoint.LOGOUT)
    @ResponseBody public ResponseVo logout(@RequestBody Member param) {
        ResponseVo response = new ResponseVo();
        return response;
    }

}
