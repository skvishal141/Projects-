package com.jfs.training.bean;

import javax.validation.constraints.NotEmpty;

/**
 * Represents a task in the Spring Boot application.
 * This bean is typically used as a request/response DTO
 * and includes Bean Validation rules for input validation.
 */
public class TaskBean {

    /* Unique identifier of the task */
    private Long id;

    /* Title of the task. Must not be empty */
    @NotEmpty(message = "Task title is required")
    private String title;

    /* Description of the task */
    private String description;

    /* Whether the task has been completed */
    private Boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
