package com.jfs.training.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jfs.training.bean.TaskBean;
import com.jfs.training.services.TaskServiceImpl;

/*
 * REST Controller class for managing task related web requests.
 * Handles operations like:
 * - saving tasks (/save)
 * - listing all tasks (/list)
 * - marking a task as complete (/complete/{id})
 * - deleting a task (/delete/{id})
 * - centralized exception handling
 *
 * All endpoints in this controller require an authenticated user because of
 * the rules configured in SecurityConfig.
 */
@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskServiceImpl taskServiceImpl;

	/**
	 * To-Do Item 1.10:
	 *   This method should save a new task submitted from the client.
	 *
	 * TODO:
	 *   --Map the URL to /save using RequestMethod.POST.
	 *   --Perform validation checks (BindingResult).
	 *   --If validation fails, return BAD_REQUEST along with validation error details.
	 *   --On successful validation, invoke the addTask method of taskServiceImpl.
	 *   --Return CREATED status with a success message after successful save.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveTask(@Valid @ModelAttribute TaskBean task, BindingResult result) throws Exception {
		//  the wildcard<?> used above allows to return different body types .
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		taskServiceImpl.addTask(task);
		return new ResponseEntity<>("Task added successfully ",HttpStatus.CREATED); // Participant has to complete
	}

	/**
	 * To-Do Item 1.11:
	 *   This method should fetch all tasks from the database.
	 *
	 * TODO:
	 *   --Map the URL to /list using RequestMethod.GET.
	 *   --Invoke the getAllTasks method of taskServiceImpl.
	 *   --Return OK status with the task list as response body.
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ResponseEntity<List<TaskBean>> listTasks() throws Exception {
		ResponseEntity<List<TaskBean>> List= (ResponseEntity<List<TaskBean>>)taskServiceImpl.getAllTasks();
		return List; // Participant has to complete
	}

	/**
	 * To-Do Item 1.12:
	 *   This method should mark a task as completed.
	 *
	 * TODO:
	 *   --Map the URL to /complete/{id} using RequestMethod.PUT.
	 *   --Read the id from the path using @PathVariable.
	 *   --Invoke the markTaskComplete method of taskServiceImpl.
	 *   --Return OK status with the updated task as response body.
	 */
	@RequestMapping(value="/complete/{id}",method = RequestMethod.GET) // {} this tell the spring that term used in it is not a static its variable
	// which used to handle multiple similar terms
	public ResponseEntity<TaskBean> completeTask(@PathVariable Long id) throws Exception {
		TaskBean updatedTask= taskServiceImpl.markTaskComplete(id);
		ResponseEntity<TaskBean> done = new ResponseEntity<>(updatedTask, HttpStatus.OK);
		return done; // Participant has to complete
	}

	/**
	 * To-Do Item 1.13:
	 *   This method should delete a task by its id.
	 *
	 * TODO:
	 *   --Map the URL to /delete/{id} using RequestMethod.DELETE.
	 *   --Read the id from the path using @PathVariable.
	 *   --Invoke the deleteTask method of taskServiceImpl.
	 *   --Return OK status with a success message.
	 */
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(@PathVariable Long id) throws Exception {
		taskServiceImpl.deleteTask(id);// this returning void therefore we do not returned it dirstly
		return null; // Participant has to complete
	}

	/* Handles all uncaught exceptions */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllExceptions(Exception exception) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
	}
}
