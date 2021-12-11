package com.rawinng.todolistbackend.repo;

import com.rawinng.todolistbackend.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepo extends MongoRepository<Todo, String> {
}
