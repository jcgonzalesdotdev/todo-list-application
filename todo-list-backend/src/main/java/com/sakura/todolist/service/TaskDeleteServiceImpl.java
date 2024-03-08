package com.sakura.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakura.todolist.repository.TaskRepository;

@Service
public class TaskDeleteServiceImpl implements TaskDeleteService{

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
	
	
}
