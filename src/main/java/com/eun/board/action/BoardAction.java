package com.eun.board.action;

import com.eun.board.service.BoardService;
import com.eun.board.vo.Bbs;
import com.eun.common.security.services.UserDetail;
import com.eun.constants.ResponseCodes;
import com.eun.constants.ResponseVo;
import com.eun.member.vo.Member;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardAction {

    @Autowired
    BoardService boardService;

    /** 게시판 글 list */
    @GetMapping(value="/freeboard/list")
    public String list(){
        return "/board/freeboard";
    }

    @PostMapping(value="/freeboard/list")
    @ResponseBody public ResponseVo list(Model model){
        ResponseVo response = new ResponseVo();
        List<Bbs> list = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            Bbs bbs = new Bbs();
            bbs.setSeq(i);
            bbs.setTitle(i + "title");
            bbs.setContent(i + "content");
            list.add(bbs);
        }

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessages(ResponseCodes.SUCCESS.getMessage());
        response.setData(list);
        return response;
    }

    /**게시판 insert */
    @PostMapping(value = "/freeboard/add")
    @ResponseBody public ResponseVo add(@RequestBody Bbs param, Member member, Authentication authentication){
        ResponseVo response = new ResponseVo();

        UserDetail user = (UserDetail) authentication.getPrincipal();
        //member email을  bbs email 치환
        param.setEmail(member.getEmail());
        param.setName(user.getUsername());

        //service 전달
        int result = boardService.add(param);

        if(0 < result){
            response.setData(param);
        }

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessages(ResponseCodes.SUCCESS.getMessage());

        return response;
    }



}


















