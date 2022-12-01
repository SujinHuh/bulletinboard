package com.eun.board.mapper;

import com.eun.board.vo.Bbs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardAdminMapper {

    @Select("SELECT " +
            "   seq" +
            "   ,title" +
            " FROM" +
            "   bbs bbs ,member member" +
            " WHERE" +
            "   bbs.member_seq = member.seq" +
            "   <if test=\"bbsCd eq null or bbsCd eq ''\">" +
            "      AND bbsCd = #{bbsCd}" +
            "   </if>")
    List<Bbs> getList(int page, String bbsCd, String search);

}
