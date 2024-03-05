package com.sakura.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task updateTask(Long id, Task inTaskEntity) {
		
		Optional<Task> taskResult = taskRepository.findById(id);
		
		taskResult.get().setTitle(inTaskEntity.getTitle());
		taskResult.get().setDescription(inTaskEntity.getDescription());
		taskResult.get().setStatus(inTaskEntity.getStatus());
		
		return taskRepository.save(taskResult.get());
	}
}
