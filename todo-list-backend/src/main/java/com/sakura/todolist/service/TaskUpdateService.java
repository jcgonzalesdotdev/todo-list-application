package com.sakura.todolist.service;

import com.sakura.todolist.model.Task;

public interface TaskUpdateService {
	Task updateTask(Long id, Task taskEntity);
}
