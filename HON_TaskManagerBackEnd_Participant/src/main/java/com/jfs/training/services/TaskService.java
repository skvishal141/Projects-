package com.jfs.training.services;

import java.util.List;

import com.jfs.training.bean.TaskBean;

/* Defines the business operations that can be performed on TaskBean objects */
public interface TaskService {

	void addTask(TaskBean task) throws Exception;

	List<TaskBean> getAllTasks() throws Exception;

	TaskBean markTaskComplete(Long id) throws Exception;

	void deleteTask(Long id) throws Exception;
}
