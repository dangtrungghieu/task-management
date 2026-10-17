package com.project.task_management.controller;

import com.project.task_management.dto.TaskRequest;
import com.project.task_management.dto.TaskResponse;
import com.project.task_management.dto.TaskUpdate;
import com.project.task_management.dto.TaskUpdateState;
import com.project.task_management.entity.Task;
import com.project.task_management.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/tasks")
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest.title(), taskRequest.content());
    }
    @GetMapping("/tasks")
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    @PutMapping("/tasks/{id}")
    public TaskResponse updateTaskById(@PathVariable Long id, @RequestBody TaskUpdate taskUpdate) {
        return taskService.updateTaskById(id, taskUpdate.title(), taskUpdate.content());
    }
    @PatchMapping("/tasks/{id}")
    public TaskResponse changeTaskState(@PathVariable Long id, @RequestBody TaskUpdateState taskUpdateState) {
        return taskService.updateTaskById(id, taskUpdateState.state());
    }


}
