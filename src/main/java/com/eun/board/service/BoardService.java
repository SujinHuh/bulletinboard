package com.eun.board.service;

import com.eun.board.mapper.BoardMapper;
import com.eun.board.vo.Bbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public int add(Bbs param) {
        return boardMapper.add(param);
    }

}
