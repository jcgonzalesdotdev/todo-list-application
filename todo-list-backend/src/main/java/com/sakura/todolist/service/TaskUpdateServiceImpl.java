package com.sakura.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakura.todolist.common.CommonUtils;
import com.sakura.todolist.dto.TaskDto;
import com.sakura.todolist.exceptions.EntityNotFoundException;
import com.sakura.todolist.model.Task;
import com.sakura.todolist.repository.TaskRepository;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto updateTask(Long id, TaskDto inTaskDto) {

		Optional<Task> taskResult = taskRepository.findById(id);

		if (taskResult.isPresent()) {
			taskResult.get().setTitle(inTaskDto.getTitle());
			taskResult.get().setDescription(inTaskDto.getDescription());
			taskResult.get().setStart_date(CommonUtils
			        .stringToDateFormatterYyyyMmDd(inTaskDto.getStart_date()));
			taskResult.get().setEnd_date(CommonUtils
			        .stringToDateFormatterYyyyMmDd(inTaskDto.getEnd_date()));
			taskResult.get().setStatus(inTaskDto.getStatus());
		} else {
			throw new EntityNotFoundException("Selected task not found");
		}

		TaskDto taskOutDto = CommonUtils
		        .setTaskDto(taskRepository.save(taskResult.get()));

		return taskOutDto;
	}
}
