package com.sakura.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakura.todolist.common.CommonUtils;
import com.sakura.todolist.dto.TaskDto;
import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;

@Service
public class TaskCreateServiceImpl implements TaskCreateService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public TaskDto createTask(TaskDto inTaskDto) {
		
		Task taskEntity = taskRepository.save(CommonUtils.setTaskEntity(inTaskDto));
		TaskDto outTaskDto = CommonUtils.setTaskDto(taskEntity);
		
		return outTaskDto;
	}
}
