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

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

    /** 댓글 add */
    @PostMapping(value = "/comment/list/add")
    @ResponseBody public ResponseVo add(@RequestBody Comment param, Authentication authentication) {
        ResponseVo response = new ResponseVo();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        param.setMemberSeq(user.getMember().getSeq());

        log.info(param.toString());
        log.info("user.getMember.toString" + user.getMember().toString());
        // service 전달
        // 여기서 insert가 정상적으로됐으면
        // param에는 seq값이 들어있다.
        int result = commentService.add(param);
        log.info(param.toString());
        if(0 < result){
//            param = commentService.getComment(param.getSeq());
            response.setData(param);
            log.info("param.seq로 꺼내온 것" + param.toString());
        }

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

}
