package com.eun.board.action;

import com.eun.board.service.BoardAdminService;
import com.eun.board.vo.Bbs;
import com.eun.constants.ResponseCodes;
import com.eun.constants.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class BoardAdminAction {

    @Autowired
    BoardAdminService boardAdminService;

    @GetMapping(value = "/admin/bbs/list")
    public String list(){
        return "/admin/bbs/list";
    }

    @PostMapping(value = "/admin/bbs/list")
    public ResponseVo list(@RequestParam int page,@RequestParam String bbsCd,@RequestParam String search){
        ResponseVo response = new ResponseVo();

        log.info(page + "");
        log.info(bbsCd);
        log.info(search);

        List<Bbs> list = boardAdminService.getList(page, bbsCd, search);


        response.setData(list);
        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

}
