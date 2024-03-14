package com.sakura.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.sakura.todolist.service.TaskDeleteServiceImpl;
import com.sakura.todolist.service.TaskSearchServiceImpl;
import com.sakura.todolist.service.TaskUpdateServiceImpl;

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

	@InjectMocks
	private TaskUpdateServiceImpl taskUpdateServiceImpl;

	@InjectMocks
	private TaskDeleteServiceImpl taskDeleteServiceImpl;

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
	 * Expected result: Search result is 0, throws EntityNotFoundException
	 */
	@Test
	void test002() {

		List<Task> mockedListOfTask = new ArrayList<Task>();

		when(taskRepository.findAll()).thenReturn(mockedListOfTask);
		assertThrows(EntityNotFoundException.class,
		        () -> taskSearchServiceImpl.getAllTasks());

	}

	/**
	 * Name: test0003 
	 * Condition: Normal input Expected result: Result is not
	 * null, data successfully registered in DB
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

	/**
	 * Name: test0004 
	 * Condition: Normal input, data to update exists 
	 * Expected result: Update successfully
	 */
	@Test
	void test004() {

		Task mockTask = new Task();
		mockTask.setId((long) 1);
		mockTask.setTitle("Sample Title");
		mockTask.setDescription("Sample Description");


		TaskDto mockTaskDto = new TaskDto();
		mockTaskDto.setId((long) 1);
		mockTaskDto.setTitle("Sample Title");
		mockTaskDto.setDescription("Sample Description");

		Optional<Task> mockOptTask = Optional.of(mockTask);

		when(taskRepository.findById(anyLong())).thenReturn(mockOptTask);
		when(taskRepository.save(mockOptTask.get())).thenReturn(mockTask);

		TaskDto serviceOutput = taskUpdateServiceImpl
		        .updateTask(mockTaskDto.getId(), mockTaskDto);
		assertEquals(mockTaskDto.getId(), serviceOutput.getId());
		assertEquals(mockTaskDto.getTitle(), serviceOutput.getTitle());
		assertEquals(mockTaskDto.getDescription(), serviceOutput.getDescription());
	}
	
	/**
	 * Name: test0005 
	 * Condition: Normal input, data to update does not exist
	 * Expected result: Throw EntityNotFoundException
	 */
	@Test
	void test005() {

		TaskDto mockTaskDto = new TaskDto();
		mockTaskDto.setId((long) 1);
		mockTaskDto.setTitle("Sample Title");
		mockTaskDto.setDescription("Sample Description");

		when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(EntityNotFoundException.class, () -> taskUpdateServiceImpl
		        .updateTask(mockTaskDto.getId(), mockTaskDto));
	}
	
	/**
	 * Name: test0005 
	 * Condition: Normal input
	 * Expected result: Delete data successfully
	 */
	@Test
	void test006() {
		taskDeleteServiceImpl.deleteTask(anyLong());
		}

}
