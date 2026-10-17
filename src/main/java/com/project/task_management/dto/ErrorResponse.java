package com.project.task_management.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ErrorResponse(String timestamp, int status, String message) {
}
