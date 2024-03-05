package com.nexi.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nexi.todolist.entity.Task;
import com.nexi.todolist.repository.TaskRepository;

@SpringBootApplication
public class TodoListApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void run(String... args) throws Exception {
	
		Task task1 = Task.builder().title("Task1")
				.description("Task 1 description")
				.start_date(null)
				.end_date(null)
				.status("0")
				.build();
		
		taskRepository.save(task1);
	}

}
