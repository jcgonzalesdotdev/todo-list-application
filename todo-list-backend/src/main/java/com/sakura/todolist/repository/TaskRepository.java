package com.sakura.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakura.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
