package com.sakura.todolist.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sakura.todolist.dto.TaskDto;
import com.sakura.todolist.model.Task;

public class CommonUtils {
	
	private CommonUtils() {
	}
	
	/**
	 * 
	 * Format Date to String (yyyyMmDd)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStringFormatterYyyyMmDd(Date date) {
		 String formattedDate = CommonConstants.EMPTY;
		 
		 if(date != null) {
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 formattedDate = formatter.format(date);
		 }
		 
		 return formattedDate;
	}
	
	/**
	 * 
	 * Format String to Date (yyyy-Mm-dd)
	 * @param dateString
	 * @return
	 */
	public static Date stringToDateFormatterYyyyMmDd(String dateString) {
		Date date = null;
		if (dateString != null && !dateString.isEmpty()) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				date = formatter.parse(dateString);
			} catch (ParseException e) {
				System.out.println("Parse Exception: " + e.getMessage());
			}
		}
		return date;
	}
	
	/**
	 * 
	 * Set the TaskDto to Task Entity
	 * 
	 * @param taskInDto
	 * @return
	 */
	public static Task setTaskEntity(TaskDto taskInDto) {
		
		Task taskEntity = new Task();
		
		taskEntity.setId(taskInDto.getId());
		taskEntity.setTitle(taskInDto.getTitle());
		taskEntity.setDescription(taskInDto.getDescription());
		taskEntity.setStart_date(stringToDateFormatterYyyyMmDd(taskInDto.getStart_date()));
		taskEntity.setEnd_date(stringToDateFormatterYyyyMmDd(taskInDto.getEnd_date()));
		taskEntity.setStatus(taskInDto.getStatus());
		
		return taskEntity;
	}
	
	/**
	 * 
	 * Set the Task Entity to TaskDto
	 * 
	 * @param taskEntity
	 * @return
	 */
	public static TaskDto setTaskDto(Task taskEntity) {
		TaskDto taskDto = new TaskDto();
		
		taskDto.setId(taskEntity.getId());
		taskDto.setTitle(taskEntity.getTitle());
		taskDto.setDescription(taskEntity.getDescription());
		taskDto.setStart_date(dateToStringFormatterYyyyMmDd(taskEntity.getStart_date()));
		taskDto.setEnd_date(dateToStringFormatterYyyyMmDd(taskEntity.getEnd_date()));
		taskDto.setStatus(taskEntity.getStatus());
		
		return taskDto;
	}
}
