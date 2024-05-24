package com.todo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.app.model.Todo;

public interface TodoRepository extends MongoRepository<Todo, String> {
}
