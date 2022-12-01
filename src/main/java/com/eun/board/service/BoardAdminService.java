package com.eun.board.service;

import com.eun.board.mapper.BoardAdminMapper;
import com.eun.board.vo.Bbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardAdminService {

    @Autowired
    BoardAdminMapper boardAdminMapper;

    public List<Bbs> getList(int page, String bbsCd, String search){
        List<Bbs> list = boardAdminMapper.getList(page, bbsCd, search);

        return list;
    }
}
