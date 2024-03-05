package com.sakura.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakura.todolist.model.Task;
import com.sakura.todolist.service.TaskUpdateService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskUpdateService taskUpdateService;

	/**
	 * Update details of the selected task
	 * 
	 * @param id
	 * @param taskEntity
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task inputTask) {
		Task task = taskUpdateService.updateTask(id, inputTask);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
}
