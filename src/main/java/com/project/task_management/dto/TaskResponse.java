package com.project.task_management.dto;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String title, String content, Integer state,String createdAt) {
}
