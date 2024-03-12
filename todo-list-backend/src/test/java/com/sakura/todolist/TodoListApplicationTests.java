package com.sakura.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sakura.todolist.dto.TaskDto;
import com.sakura.todolist.exceptions.EntityNotFoundException;
import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;
import com.sakura.todolist.service.TaskCreateServiceImpl;
import com.sakura.todolist.service.TaskSearchServiceImpl;

@SpringBootTest
class TodoListApplicationTests {
	
	@BeforeEach
	public void setUp() {
	}
	
	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private TaskSearchServiceImpl taskSearchServiceImpl;
	
	@InjectMocks
	private TaskCreateServiceImpl taskCreateServiceImpl;

	/**
	 * Name: Test0001
	 * Condition: Normal input
	 * Expected result: Return normal result
	 */
	@Test
	void test001() {
		
		List<Task> mockedListOfTask = new ArrayList<Task>();
		
		Task task = new Task();
		task.setId((long) 1);
		task.setTitle("Title 1");
		mockedListOfTask.add(task);
		
		when(taskRepository.findAll()).thenReturn(mockedListOfTask);
		assertNotEquals(0, taskSearchServiceImpl.getAllTasks().size());
	}
	
	/**
	 * Name: Test0002
	 * Condition: Normal input
	 * Expected result: Result is 0, throws EntityNotFoundException	
	 */
	@Test
	void test002() {
		
		List<Task> mockedListOfTask = new ArrayList<Task>();
		
		
		when(taskRepository.findAll()).thenReturn(mockedListOfTask);
		assertThrows(EntityNotFoundException.class, () -> taskSearchServiceImpl.getAllTasks());
		
	}
	
	/**
	 * Name: test0003
	 * Condition: Normal input
	 * Expected result: 
	 */
	@Test
	void test003() {
		
		List<Task> mockedListOfTask = new ArrayList<Task>();
		
		Task mockTask = new Task();
		mockTask.setId((long) 1);
		mockTask.setTitle("Sample Title");
		mockTask.setDescription("Sample Description");

		mockedListOfTask.add(mockTask);
		 
		TaskDto mockTaskDto = new TaskDto();
		mockTaskDto.setId((long) 1);
		mockTaskDto.setTitle("Sample Title");
		mockTaskDto.setDescription("Sample Description");

		when(taskRepository.save(mockTask)).thenReturn(mockTask);
		assertNotEquals(null, taskCreateServiceImpl.createTask(mockTaskDto));
	}

}
