package com.sakura.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;
import com.sakura.todolist.service.TaskSearchService;
import com.sakura.todolist.service.TaskSearchServiceImpl;

@SpringBootTest
class TodoListApplicationTests {
	
	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private TaskSearchServiceImpl taskSearchServiceImpl;

	/**
	 * Name: Test0001
	 * Condition: Normal input
	 * Expected result: Return normal result
	 */
	@Test
	void test001() {
		
		List<Task> mockedListOfTask = new ArrayList<Task>();
		
		Task task = new Task();
		task.setId(Mockito.anyLong());
		task.setTitle(Mockito.anyString());
		mockedListOfTask.add(task);
		
		when(taskRepository.findAll()).thenReturn(mockedListOfTask);
		assertNotEquals(0, taskSearchServiceImpl.getAllTasks().size());
	}

}
