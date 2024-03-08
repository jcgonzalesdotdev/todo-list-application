package com.sakura.todolist.service;

import java.util.List;

import com.sakura.todolist.dto.TaskDto;

public interface TaskSearchService {
	List<TaskDto> getAllTasks();
}
