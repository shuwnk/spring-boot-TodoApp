package com.todo.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Todo {
	@Id
	private String id;
	private String title;
	private boolean completed;

	// Costructors -> Default constructor
	public Todo() {
	}

	public Todo(String title, boolean completed) {
		this.title = title;
		this.completed = completed;
	}

	// Getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
