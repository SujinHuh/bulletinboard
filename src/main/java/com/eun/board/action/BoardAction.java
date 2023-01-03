package com.eun.board.action;

import com.eun.board.service.BoardService;
import com.eun.board.vo.Bbs;
import com.eun.common.exception.BusinessException;
import com.eun.common.security.services.UserDetail;
import com.eun.constants.ResponseCodes;
import com.eun.constants.ResponseVo;
import com.eun.member.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
public class BoardAction {

    @Autowired
    BoardService boardService;

    /** 게시판 글 list */
    @GetMapping(value="/freeboard/list")
    public String list(){
        return "/board/list";
    }

    @PostMapping(value="/freeboard/list")
    @ResponseBody public ResponseVo list(String temp){
        ResponseVo response = new ResponseVo();

        List<Bbs> list = boardService.getList();

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        response.setData(list);
        return response;
    }

    @GetMapping(value="/freeboard/add")
    public String add(){
        return "/board/add";
    }

    /**게시판 insert */
    @PostMapping(value = "/freeboard/add")
    @ResponseBody public ResponseVo add(@RequestBody Bbs param, Authentication authentication){
        ResponseVo response = new ResponseVo();
        //스프링시큐리티 자체가 authentication 이객체에 세션을 만듬
        UserDetail user = (UserDetail) authentication.getPrincipal();
        Member member = user.getMember();
        //member email을  bbs email 치환
        param.setBbsCd("FR");
        param.setName(member.getName());
        param.setEmail(member.getEmail());
        param.setMemberSeq(member.getSeq());
        log.info("로그인정보 {}", member.toString());
        log.info("작성글 정보 {}", param.toString());

        // service 전달 전 화면에서 올라온 데이터들이 정확한지 검증하는 밸리데이션 추가

        //service 전달
        int result = boardService.add(param);

        if(0 < result){
            response.setData(param);
        }
        // 프로세스가 정상적으로 성공을하고 리턴이되면 이결 다시 정상으로 대입시키죠.
        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());

        return response;
    }

    /**게시판 글 view */
    @GetMapping(value = "/board/view/{seq}")
    public String view(){return "/board/view";}

    //  BoardAction-view() 3. axios의 요청이 해당컨트롤러로 매핑
    // parameter로 넘겨준 seq는 PathVariable 어노테이션에 의해 seq에 매핑이됩니다.
    @PostMapping(value = "/board/view/{seq}")
    @ResponseBody public ResponseVo view(@PathVariable String seq, Authentication authentication){
        // 여기서  부정으로 생성
        ResponseVo response = new ResponseVo();
        // 세션에서 유저정보 확인
        UserDetail user = (UserDetail) authentication.getPrincipal();
        Member member = user.getMember();
        // 그러면 해당 seq가지고
        log.info(seq);
        // 0. seq가지고 게시글을 가져온다
        Bbs bbs = boardService.getView(seq);
        // 1.게시글이 없을 경우 (validation 처리)
        if(bbs == null){
            //게시글 없어 err
            throw new BusinessException(ResponseCodes.BOARD_NULL);
        }

        // 2.게시글이 privetYN -> Y경우 (나와 관리자만 보여야함)
        if(member.getSeq() != bbs.getMemberSeq() && "Y".equals(bbs.getPrivateYn())){
               /* log.info("getPrivateYES",bbs.toString());
                response.setMessage("게시글 Private Y");
                response.setData("privateY");
                return response;*/
            throw new BusinessException(ResponseCodes.BOARD_PRIEVATE);
        }
        // 3.게시글이 내가 작성한 게시글일 경우 (수정/삭제 버튼) -> deleteY
        if(member.getSeq() == bbs.getMemberSeq()){
            bbs.setMyBbs(true);
//            if("N".equals(bbs.getDeleteYn())){
//                throw new BusinessException(ResponseCodes.BOARD_MODIFY);
//            }
//            if("Y".equals(bbs.getDeleteYn())){
//                throw new BusinessException(ResponseCodes.BOARD_DELETE);
//            }

        }

        log.info(member.toString());
        log.info(bbs.toString());

        // 4. 게시글을 클릭했으면 조회수를 올린다.s
//        boardService.bbsCounting(bbs.getSeq());

        // 프로세스가 정상적으로 성공을하고 리턴이되면 이결 다시 정상으로 대입.
        response.setData(bbs);
        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

    /**게시판 글 Modify */
    @GetMapping(value = "/board/modify/{seq}")
    public String modify() {return "/board/modify";}

    @PostMapping(value = "/board/modify")
    @ResponseBody public ResponseVo modify(@RequestBody Bbs param, Authentication authentication) {

        log.info("param >>> " + param.toString());

        ResponseVo response = new ResponseVo();

        //세션 유저정보 확인
        UserDetail user = (UserDetail) authentication.getPrincipal();

        // 지금 현재 문제점은 게시글 번호만 받고있기에
        // 수정될 내용을 안받고있다.
        // 게시글 수정은 등록과 동일하다.
        // 그렇다면 우선 수정될 내용을 받아야한다.

        param.setMemberSeq(user.getMember().getSeq());

        // bbs seq가 위변조되었는지 한번더 권한을 확인을해
        Bbs bbs2 = boardService.getView(String.valueOf(param.getSeq()));

        if (bbs2.getMemberSeq() != user.getMember().getSeq() ) {
             throw new BusinessException(ResponseCodes.BOARD_ERR);
        }

        // 그러고나서 업데이트
        boardService.modify(param);

        // 게시글 modify && view same

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        return response;
    }

    @PostMapping(value = "/board/delete/{seq}")
    @ResponseBody public ResponseVo delete(@PathVariable int seq, Authentication authentication) {

        log.info("seq >>> " + seq );

        ResponseVo response = new ResponseVo();
        //세션 유저정보 확인
        UserDetail user = (UserDetail) authentication.getPrincipal();
        // User Seq
        log.info("user.getMember.getSeq >>>>"+ user.getMember().getSeq());
        Member member = user.getMember();
        Bbs bbs = boardService.getView(String.valueOf(seq));

        // update 할때 조건문에 member.seq넣어서 본인확인도해주는게좋을듯싶다!
        if(user.getMember().getSeq() != bbs.getMemberSeq()) { //게시판의 seq
            throw new BusinessException(ResponseCodes.BOARD_ERR);
        }

//        boardService.delete(seq);

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        return response;

    }
}


















