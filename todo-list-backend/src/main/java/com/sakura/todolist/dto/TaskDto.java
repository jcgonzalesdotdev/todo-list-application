package com.sakura.todolist.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
	
    private Long id;
	
    private String title;
	
    private String description;
	
    private String start_date;
	
    private String end_date;
	
	private String status;

}
