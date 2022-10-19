package com.eun.todo.service;

import com.eun.todo.mapper.TodoMapper;
import com.eun.todo.vo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired TodoMapper todoMapper;

    public int add(Todo param) {
        return todoMapper.add(param);
    }

    public List<Todo> list(String email) {
        return todoMapper.list(email);
    }
}
