package com.eun.process.member.genMember.action;

import com.eun.common.ResponseCodes;
import com.eun.common.ResponseVo;
import com.eun.process.member.genMember.vo.MemberVo;
import com.eun.property.Endpoint;
import com.eun.property.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class GenerateMemberAction {

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


    // api
    @PostMapping(value = Endpoint.LOGIN)
    @ResponseBody public ResponseVo login(@RequestBody MemberVo param) {
        ResponseVo response = new ResponseVo();
        log.info("login!!");

        log.info("param >>> {}", param);

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
