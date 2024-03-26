package com.sakura.todolist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
	
    private Long id;
	
    private String title;
	
    private String description;
	
    private String start_date;
	
    private String end_date;
	
	private String status;

}
