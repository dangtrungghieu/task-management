package com.project.task_management.exception;
import com.project.task_management.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

import static com.project.task_management.DateUtils.FORMATTER;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleRuntimeException(RuntimeException exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        String timestampFormat = FORMATTER.format(timestamp);
        return new ErrorResponse(
                timestampFormat,
                404,
                exception.getMessage()
        );
    }
}
