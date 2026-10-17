package com.project.task_management.service;

import com.project.task_management.dto.TaskResponse;
import com.project.task_management.entity.Task;
import com.project.task_management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.project.task_management.DateUtils.FORMATTER;

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
    //2. Get all task
    public List<TaskResponse> getAllTasks(){
        return taskRepository.findAll().stream().map(this::mapToResponse).toList();
    }
    private TaskResponse mapToResponse(Task task){
        Long id = task.getId();
        String title = task.getTitle();
        String content = task.getContent();
        Integer state = task.getState();
        LocalDateTime createdAt = task.getCreatedAt();
        String createAtFormatted = FORMATTER.format(createdAt);
        return new TaskResponse(id,title,content,state,createAtFormatted);
    }
    //3. Get by id
    public TaskResponse getTaskById(Long id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy công việc với ID: " + id));
        return mapToResponse(task);
    }
    //4. Update (PUT)
    public TaskResponse updateTaskById(Long id, String title, String content){
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy công việc với ID: " + id));
        task.setTitle(title);
        task.setContent(content);
        return mapToResponse(taskRepository.save(task));
    }
    //5. Update (PATCH)
    public TaskResponse updateTaskById(Long id, Integer state){
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy công việc với ID: " + id));
        if(state == 1 || state == 2 || state == 3){
            task.setState(state);
        }
        return mapToResponse(taskRepository.save(task));
    }
    //6. Delete (DELETE)
    public void deleteTaskById(Long id){
        if(!taskRepository.existsById(id)){
            throw new RuntimeException("Không tìm thấy task ID: " + id);
        }
        taskRepository.deleteById(id);
    }
}
