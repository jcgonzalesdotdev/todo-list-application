package com.sakura.todolist.service;

import java.util.List;

import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskSearchServiceImpl implements TaskSearchService {
	
	private final TaskRepository taskRepository;

    @Autowired
    public TaskSearchServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

	@Override
	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
	}

}
