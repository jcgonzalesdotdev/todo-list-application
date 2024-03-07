package com.sakura.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakura.todolist.exceptions.EntityNotFoundException;
import com.sakura.todolist.model.Task;
import com.sakura.todolist.service.TaskUpdateService;
import com.sakura.todolist.service.TaskDeleteService;
import com.sakura.todolist.service.TaskSearchService;
import com.sakura.todolist.service.TaskCreateService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskUpdateService taskUpdateService;

	@Autowired
	private TaskSearchService taskSearchService;
	
	@Autowired
	private TaskDeleteService taskDeleteService;

	@Autowired
	private TaskCreateService taskCreateService;
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
		try {
			Task updatedTask = taskUpdateService.updateTask(id, inputTask);
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
	public ResponseEntity<?> getAllTasks() {
		try {
			List<Task> tasks = taskSearchService.getAllTasks();
			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        taskDeleteService.deleteTask(id);

        try {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
        
    }
    
    /**
     * Create new task
     * 
     * @return
     */
    @PostMapping("/add")
    public Task createTask(@RequestBody Task task) {
    	return taskCreateService.createTask(task);
    }
}
