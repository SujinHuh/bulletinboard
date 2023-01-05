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
    public List<Comment> list(int seq) {return commentMapper.list(seq);}
}
