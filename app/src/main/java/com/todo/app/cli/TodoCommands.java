package com.todo.app.cli;

import com.todo.app.model.Todo;
import com.todo.app.repository.TodoRepository;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoCommands implements CommandLineRunner {

	@Autowired
	private TodoRepository todoRepository;

	private final Scanner scanner = new Scanner(System.in);

	@Override
	public void run(String... args) {

		while (true) {
			System.out.println("Todo CLI App");
			System.out.println("1. Create Todo");
			System.out.println("2. List Todo");
			System.out.println("3. Update Todo");
			System.out.println("4. Delete Todo");
			System.out.println("5. Exit");
			System.out.println("Enter your choice: ");

			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {

				case 1:
					createTodo();
					break;
				case 2:
					listTodos();
					break;
				case 3:
					updateTodo();
					break;
				case 4:
					deleteTodo();
					break;
				case 5:
					return;
				default:
					System.out.println("Invalid choice. Please Try again.");

			}
		}
	}

	private void createTodo() {
		System.out.println("Enter title: ");
		String title = scanner.nextLine();

		Todo todo = new Todo();
		todo.setTitle(title);
		todo.setCompleted(false);

		todoRepository.save(todo);
		System.out.println("Todo created succesfully!");

	}

	private void listTodos() {

		System.out.println("List of Todos:");

		for (Todo todo : todoRepository.findAll()) {
			System.out.println(todo.getId() + ". " + todo.getTitle() + " - "
					+ (todo.isCompleted() ? "Completed" : "Pending"));
		}
	}

	private void updateTodo() {
		System.out.println("Enter Todo ID to update: ");
		String id = scanner.nextLine();
		Optional<Todo> tOptional = todoRepository.findById(id);
		if (tOptional.isPresent()) {
			Todo todo = tOptional.get();
			System.out.println("Enter new title (current: " + todo.getTitle() + "): ");
			String title = scanner.nextLine();
			System.out.println("Is completed ? (true/false): ");

			boolean completed = Boolean.parseBoolean(scanner.nextLine());
			todo.setTitle(title);
			todo.setCompleted(completed);
			todoRepository.save(todo);
			System.out.println("Todo updated successfully!");
		} else {
			System.out.println("Todo not found.");
		}
	}

	private void deleteTodo() {
		System.out.println("Enter Todo ID to delete: ");
		String id = scanner.nextLine();
		todoRepository.deleteById(id);
		System.out.println("Todo deleted successfully");
	}
}
