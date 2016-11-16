package com.devops.service;

import com.devops.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SQLI on 16/11/16.
 */
@Service
public class TodoService {

    private List<Todo> todos;

    public List<Todo> getAll(){
        this.todos = new ArrayList<>();
        Todo todo = new Todo();
        todo.setId(Long.valueOf(1));
        todo.setResponsable("Mounir");
        todo.setTexte("du texte");
        this.todos.add(todo);
        return this.todos;
    }
}
