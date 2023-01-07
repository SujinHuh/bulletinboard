package com.eun.comment.action;

import com.eun.comment.service.CommentService;
import com.eun.comment.vo.Comment;
import com.eun.common.security.services.UserDetail;
import com.eun.constants.ResponseCodes;
import com.eun.constants.ResponseVo;
import groovy.util.logging.Commons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class CommentAction {
    @Autowired
    CommentService commentService;

    /** 댓글 view */
    @PostMapping(value = "/comment/list/{boardSeq}")//list를 가져오지 않고 list_seq를 가져올 수 없음.
    @ResponseBody public ResponseVo list (@PathVariable String boardSeq, Authentication authentication) {
        ResponseVo response = new ResponseVo();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        // bbs seq ->를 받아야 함.
        log.info("user.getUserName" + user.getUsername());
        log.info("user.getMember.getName" + user.getMember().toString());

        List<Comment> list = commentService.list(boardSeq);
        response.setData(list);
        log.info(list.toString());

        response.setData(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

    @PostMapping(value = "/comment/list/add")
    @ResponseBody public ResponseVo add(@RequestBody Comment param, Authentication authentication) {
        ResponseVo response = new ResponseVo();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        param.setMemberSeq(user.getMember().getSeq());

        log.info(param.toString());
        //service -> add 필요


        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

}
