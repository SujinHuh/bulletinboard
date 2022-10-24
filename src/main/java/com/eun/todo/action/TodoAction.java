package com.eun.todo.action;

import com.eun.common.property.Endpoint;
import com.eun.common.security.services.UserDetail;
import com.eun.constants.ResponseCodes;
import com.eun.constants.ResponseVo;
import com.eun.todo.service.TodoService;
import com.eun.todo.vo.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class TodoAction {

    @Autowired TodoService todoService;

    @PostMapping(value = Endpoint.TODO_LIST)
    @ResponseBody public ResponseVo list(Authentication authentication) {
        ResponseVo response = new ResponseVo();

        // 세션에서 유저정보 확인
        UserDetail user = (UserDetail) authentication.getPrincipal();
        log.info(user.getUsername());

        // 2. service 전달
        List<Todo> list = todoService.list(user.getUsername());
        log.info(list.toString());
        response.setData(list);

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessages(ResponseCodes.SUCCESS.getMessage());

        return response;
    }

    @PostMapping(value = Endpoint.TODO_ADD)
    @ResponseBody public ResponseVo add(@RequestBody Todo param, Authentication authentication) {
        ResponseVo response = new ResponseVo();

        // 세션에서 유저정보 확인
        UserDetail user = (UserDetail) authentication.getPrincipal();
        param.setEmail(user.getUsername());
//        param.setEmail(user.getMember().getEmail());

        // 1.param 밸리데이션
        log.info("param >>> {}", param.toString());
        log.info("user >>> {}", user.getMember().toString());

        // 2. service 전달
        int result = todoService.add(param);

        if(0 < result){
            response.setData(param);
        }

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessages(ResponseCodes.SUCCESS.getMessage());

        return response;
    }

}
