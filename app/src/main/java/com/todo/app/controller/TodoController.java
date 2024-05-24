package com.todo.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.model.Todo;
import com.todo.app.repository.TodoRepository;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@GetMapping
	public List<Todo> getALlTodos() {
		return todoRepository.findAll();
	}

	@PostMapping
	public Todo createTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@GetMapping("/{id}")
	public Todo getTodoById(@PathVariable String id) {
		return todoRepository.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Todo updateTodo(@PathVariable String id, @RequestBody Todo updatedTodo) {
		return todoRepository.findById(id)
				.map(todo -> {
					todo.setTitle(updatedTodo.getTitle());
					todo.setCompleted(updatedTodo.isCompleted());
					return todoRepository.save(todo);
				})
				.orElseGet(() -> {
					updatedTodo.setId(id);
					return todoRepository.save(updatedTodo);
				});
	}

	@DeleteMapping("/{id}")
	public void deleteTodo(@PathVariable String id) {
		todoRepository.deleteById(id);
	}
}
