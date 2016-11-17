package com.devops.service;

import com.devops.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by SQLI on 16/11/16.
 */
@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();

    public List<Todo> getAll(){
        List<Todo> elements = this.todos.stream().sorted((item1, item2)-> item2.getDateCreation().compareTo(item1.getDateCreation())).collect(Collectors.toList());
        return elements;
    }
    public UUID addItem(Todo item){
        item.setId(UUID.randomUUID());
        this.todos.add(item);
        return item.getId();
    }
}
