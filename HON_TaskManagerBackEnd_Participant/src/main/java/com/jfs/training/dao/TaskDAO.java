package com.jfs.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jfs.training.entity.TaskEntity;

/**
 * To-Do Item 1.1:
 *   Define a JPA repository interface for TaskEntity so that Spring Data JPA
 *   provides built-in CRUD operations (save, findAll, findById, deleteById, etc.)
 *   without requiring manual implementation.
 *
 * CRUD represents create , read ,update and delete methods provided by the JPA
 * TODO:
 *   --Make this interface extend JpaRepository, parameterized with TaskEntity
 *     as the entity type and Long as the primary key type.
 */
public interface TaskDAO extends JpaRepository<TaskEntity,Long> {

}
