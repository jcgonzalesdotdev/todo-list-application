package com.sakura.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakura.todolist.exceptions.EntityNotFoundException;
import com.sakura.todolist.model.Task;
import com.sakura.todolist.service.TaskUpdateService;
import com.sakura.todolist.service.TaskSearchService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskUpdateService taskUpdateService;

	@Autowired
	private TaskSearchService taskService;

	/**
	 * Update details of the selected task
	 * 
	 * @param id
	 * @param taskEntity
	 * @return
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTask(@PathVariable("id") Long id,
	        @RequestBody Task inputTask) {
		
		Task updatedTask = new Task();
		
		try {
			updatedTask = taskUpdateService.updateTask(id, inputTask);
			return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}

	/**
	 * Get a list of all tasks
	 *
	 * @return
	 */
	@GetMapping("/find")
	public ResponseEntity<Iterable<Task>> getAllTasks() {
		List<Task> tasks = taskService.getAllTasks();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
}
