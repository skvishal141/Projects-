package com.jfs.training.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfs.training.bean.TaskBean;
import com.jfs.training.dao.TaskDAOWrapper;

/*
 * This class acts as the business layer between controllers and DAO layer. It
 * delegates the actual data operations to TaskDAOWrapper.
 */
@Service
public class TaskServiceImpl implements TaskService {

	/* DAO wrapper to perform CRUD operations on tasks */
	@Autowired
	private TaskDAOWrapper taskDAOWrapper;

	/**
	 * To-Do Item 1.6:
	 *   This method should add a new task.
	 *
	 * TODO:
	 *   --Invoke the addTask method of taskDAOWrapper.
	 */
	@Override
	public void addTask(TaskBean task) throws Exception {
		// Participant has to complete
//		TaskDAOWrapper taskDAOWrapper = new TaskDAOWrapper();
		/* we did not used the above method because if we manualy define any
		object of any class then sprig will not mannage it ,
		its all the functions and data will be null
		 */
		taskDAOWrapper.addTask(task);
	}

	/**
	 * To-Do Item 1.7:
	 *   This method should fetch all task details.
	 *
	 * TODO:
	 *   --Invoke the getAllTasks method of taskDAOWrapper.
	 *   --Return the task list.
	 */
	@Override
	public List<TaskBean> getAllTasks() throws Exception {

		return taskDAOWrapper.getAllTasks(); // Participant has to complete
	}

	/**
	 * To-Do Item 1.8:
	 *   This method should mark a task as completed.
	 *
	 * TODO:
	 *   --Invoke the markTaskComplete method of taskDAOWrapper with the given id.
	 *   --Return the updated TaskBean.
	 */
	@Override
	public TaskBean markTaskComplete(Long id) throws Exception {
		return taskDAOWrapper.markTaskComplete(id); // Participant has to complete
	}

	/**
	 * To-Do Item 1.9:
	 *   This method should delete a task by its id.
	 *
	 * TODO:
	 *   --Invoke the deleteTask method of taskDAOWrapper with the given id.
	 */
	@Override
	public void deleteTask(Long id) throws Exception {
		// Participant has to complete
		taskDAOWrapper.deleteTask(id);
	}
}
