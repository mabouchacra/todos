package com.devops.service;

import com.devops.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by SQLI on 16/11/16.
 */
@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();

    public List<Todo> getAll(){
        return this.todos;
    }
    public UUID addItem(Todo item){
        item.setId(UUID.randomUUID());
        this.todos.add(item);
        return item.getId();
    }
}
