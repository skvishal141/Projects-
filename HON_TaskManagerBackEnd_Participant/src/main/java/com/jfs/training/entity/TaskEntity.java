package com.jfs.training.entity;

import javax.persistence.*;

/*
 * Entity class representing the "tasks" table in the database.
 * This class is mapped to a database table using JPA annotations and is used by
 * Spring Data JPA for CRUD operations.
 */
@Entity
@Table(name = "tasks")
public class TaskEntity {

	/* Primary key of the task (auto-generated) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/* Title of the task */
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
