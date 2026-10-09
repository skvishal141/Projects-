package com.jfs.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jfs.training.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * To-Do Item 2.1:
 *   Define a JPA repository interface for UserEntity.
 *
 * TODO:
 *   --Make this interface extend JpaRepository, parameterized with UserEntity
 *     as the entity type and Long as the primary key type.
 *   --Declare a method named findByUsername(String username) that returns a
 *     UserEntity. Spring Data JPA will automatically implement this method
 *     for you based on its name.
 */
@Repository
public interface UserDAO extends JpaRepository<UserEntity,Long>{
    public UserEntity findByUsername(String username);

}
