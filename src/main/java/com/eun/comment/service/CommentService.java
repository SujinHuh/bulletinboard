package com.eun.comment.service;

import com.eun.comment.Mapper.CommentMapper;
import com.eun.comment.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    public List<Comment> list(String seq) {return commentMapper.list(seq);}

    public int add(Comment param) {return commentMapper.add(param);}

    public Comment getComment(int seq) {return  commentMapper.getComment(seq);}
}
