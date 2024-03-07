package com.sakura.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakura.todolist.exceptions.EntityNotFoundException;
import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task updateTask(Long id, Task inTaskEntity) {

		Optional<Task> taskResult = taskRepository.findById(id);

		if (taskResult.isPresent()) {
			taskResult.get().setTitle(inTaskEntity.getTitle());
			taskResult.get().setDescription(inTaskEntity.getDescription());
			taskResult.get().setStart_date(inTaskEntity.getStart_date());
			taskResult.get().setEnd_date(inTaskEntity.getEnd_date());
			taskResult.get().setStatus(inTaskEntity.getStatus());
		} else {
			throw new EntityNotFoundException("Selected task not found");
		}

		return taskRepository.save(taskResult.get());
	}
}
