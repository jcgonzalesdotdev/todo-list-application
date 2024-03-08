package com.sakura.todolist.service;

import com.sakura.todolist.dto.TaskDto;

public interface TaskCreateService {
	TaskDto createTask(TaskDto inTaskDto);
}
