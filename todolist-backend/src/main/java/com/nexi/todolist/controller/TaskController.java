package com.nexi.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexi.todolist.entity.Task;
import com.nexi.todolist.repository.TaskRepository;

@RestController
@RequestMapping("/todolist")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/getalltask")
	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

}
