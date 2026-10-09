package com.jfs.training.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jfs.training.bean.TaskBean;
import com.jfs.training.entity.TaskEntity;

/*
 * Repository wrapper for TaskEntity.
 * This class acts as a DAO wrapper providing higher level operations using
 * TaskDAO. It also handles conversion between entity and bean objects.
 */
@Repository
@Transactional(transactionManager = "txManager")
public class TaskDAOWrapper {

	/* DAO for TaskEntity providing CRUD operations. */
	@Autowired
	private TaskDAO taskDAO;

	/**
	 * To-Do Item 1.2:
	 *   This method should add a new task to the database.
	 *
	 * TODO:
	 *   --Convert the incoming TaskBean to a TaskEntity (Hint: use the
	 *     convertTaskBeanToEntity utility method below).
	 *   --Save the entity using taskDAO.
	 *   --Convert the saved entity back to a TaskBean and return it.
	 */
	public TaskBean addTask(TaskBean bean) throws Exception {
		TaskEntity taskentity=convertTaskBeanToEntity(bean);
		TaskEntity savedEntity= taskDAO.save(taskentity);
		return convertTaskEntityToBean(savedEntity); // Participant has to complete
	}

	/**
	 * To-Do Item 1.3:
	 *   This method should fetch all tasks from the database.
	 *
	 * TODO:
	 *   --Fetch all task entities from the database using taskDAO.
	 *   --Convert every TaskEntity into a TaskBean and collect them in a list.
	 *   --Return the list of TaskBeans.
	 */
	public List<TaskBean> getAllTasks() throws Exception {
		List<TaskBean>arr=new ArrayList<>();
		for(TaskEntity taskEntity:taskDAO.findAll()){
			arr.add(convertTaskEntityToBean(taskEntity));
		}
		return arr; // Participant has to complete
	}

	/**
	 * To-Do Item 1.4:
	 *   This method should mark an existing task as completed.
	 *
	 * TODO:
	 *   --Find the TaskEntity by id using taskDAO (Hint: findById returns an
	 *     Optional<TaskEntity>).
	 *   --If the task exists, set its "completed" field to true and save it.
	 *   --Convert the updated entity to a TaskBean and return it.
	 *   --If the task does not exist, throw an Exception with a helpful message.
	 */
	public TaskBean markTaskComplete(Long id) throws Exception {
		Optional<TaskEntity> taskentity=taskDAO.findById(id);
		if(taskentity.isPresent()){
			TaskEntity entity=taskentity.get();// we used this get because entity wasd wrapped in the Optional<> wrapper or container .
			entity.setCompleted(true);
			TaskEntity updated =taskDAO.save(entity);
			return convertTaskEntityToBean(updated);
		}else 	throw new Exception("Task Not Found "); // Participant has to complete
	}

	/**
	 * To-Do Item 1.5:
	 *   This method should delete a task by its id.
	 *
	 * TODO:
	 *   --Delete the task with the given id using taskDAO.
	 */
	public void deleteTask(Long id) throws Exception {
//		Optional<TaskEntity> taskentity=taskDAO.findById(id);
//		if(taskentity.isPresent()){
//			taskDAO.delete(taskentity.get());
//		}else 	throw new Exception("Task Not Found ");
//		// Participant has to complete
		//instead of above we can use direct taskDAO.deleteByID(Id)
		//beacuse spring JPA automatically check and delete the data
		taskDAO.deleteById(id);
	}

	/* Converts TaskEntity to TaskBean */
	public static TaskBean convertTaskEntityToBean(TaskEntity entity) {
		TaskBean bean = new TaskBean();
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}

	/* Converts TaskBean to TaskEntity */
	public static TaskEntity convertTaskBeanToEntity(TaskBean bean) {
		TaskEntity entity = new TaskEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}
}
