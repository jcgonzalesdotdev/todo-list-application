package com.sakura.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;

@Service
public class TaskCreateServiceImpl implements TaskCreateService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
}
