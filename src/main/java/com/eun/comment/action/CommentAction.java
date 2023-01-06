package com.eun.comment.action;

import com.eun.comment.service.CommentService;
import com.eun.comment.vo.Comment;
import com.eun.common.security.services.UserDetail;
import com.eun.constants.ResponseVo;
import groovy.util.logging.Commons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class CommentAction {
    @Autowired
    CommentService commentService;

    /** 댓글 view */
    @PostMapping(value = "/comment/{boardSeq}")//list를 가져오지 않고 list_seq를 가져올 수 없음.
    @ResponseBody public ResponseVo list (@PathVariable String seq, Authentication authentication) {
        ResponseVo response = new ResponseVo();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        // bbs seq ->를 받아야 함.
        log.info(user.getMember().getName());
        commentService.list(user.getMember().getSeq());
        return response;
    }

}
