package com.sakura.todolist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakura.todolist.common.CommonUtils;
import com.sakura.todolist.dto.TaskDto;
import com.sakura.todolist.exceptions.EntityNotFoundException;
import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;


@Service
public class TaskSearchServiceImpl implements TaskSearchService {
	
	private final TaskRepository taskRepository;

    @Autowired
    public TaskSearchServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

	@Override
	public List<TaskDto> getAllTasks() {
		
		List<TaskDto> listOfTaskOutDto = new ArrayList<>();
		List<Task> searchResult = taskRepository.findAll();
		
		if (searchResult.isEmpty() ) {
			throw new EntityNotFoundException("No tasks found");
		}
		
		for(Task task: searchResult) {
			TaskDto taskDto = CommonUtils.setTaskDto(task);
			listOfTaskOutDto.add(taskDto);
		}
		
		return listOfTaskOutDto;
	}

}
