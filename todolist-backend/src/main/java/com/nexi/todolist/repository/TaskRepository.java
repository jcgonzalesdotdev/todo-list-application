package com.nexi.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexi.todolist.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
