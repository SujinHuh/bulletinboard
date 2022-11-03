package com.eun.board.mapper;

import com.eun.board.vo.Bbs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int add(Bbs parm);
}
