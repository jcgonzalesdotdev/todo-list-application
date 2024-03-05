package com.sakura.todolist.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "tasktbl")
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "title", nullable = false)
    private String title;
	
	@Column(name = "description", nullable = true)
    private String description;
	
	@Column(name = "start_date", nullable = true)
    private Date start_date;
	
	@Column(name = "end_date", nullable = true)
    private Date end_date;
	
	@Column(name = "status", nullable = true)
	private String status;
	
}
