package com.sakura.todolist.service;

import com.sakura.todolist.dto.TaskDto;

public interface TaskUpdateService {
	TaskDto updateTask(Long id, TaskDto inTaskDto);
}
