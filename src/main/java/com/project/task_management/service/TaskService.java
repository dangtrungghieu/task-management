package com.project.task_management.service;

import com.project.task_management.entity.Task;
import com.project.task_management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    //1. Create Task
    public Task createTask(String title, String content){
        Task task = new Task();
        task.setTitle(title);
        task.setContent(content);
        task.setState(1);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
}
