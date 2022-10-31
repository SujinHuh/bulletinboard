package com.eun.board.action;

import com.eun.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardAction {

    @Autowired
    BoardService boardService;

    @RequestMapping(value="freeboard/insert.do")
    public String index(Model model){

        return "freeboard/index";
    }


}
