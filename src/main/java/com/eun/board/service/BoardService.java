package com.eun.board.service;

import com.eun.board.mapper.BoardMapper;
import com.eun.board.vo.Bbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public int add(Bbs param) {
        return boardMapper.add(param);
    }
    public List<Bbs> getList(){return boardMapper.getList();}
    public Bbs getView(String seq) { return boardMapper.getView(seq);}

    public void success(int boardSeq, int memberSeq) {boardMapper.success(boardSeq, memberSeq);}

    public void delete(int boardSeq, int memberSeq) { boardMapper.delete(boardSeq, memberSeq);}
}
