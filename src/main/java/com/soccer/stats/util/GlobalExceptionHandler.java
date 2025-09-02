package com.soccer.stats.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        // Log the exception (optional)
        log.error("An error occurred: " + e.getMessage());

        // Return a view name or redirect to an error page
        return "error"; // Assuming you have an error.html or error.jsp in your templates
    }

}
