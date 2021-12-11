package com.rawinng.todolistbackend.controller;

import com.rawinng.todolistbackend.entity.Todo;
import com.rawinng.todolistbackend.repo.TodoRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Setter
    @Autowired
    private TodoRepo todoRepo;

    @GetMapping
    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return todoRepo.save(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable("id") String id, @RequestBody Todo todo) {
        if (todo.getId() == null) {
            todoRepo.findById(id).orElseThrow();
        }
        todo.setId(id);
        return todoRepo.save(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        todoRepo.deleteById(id);
    }
}
