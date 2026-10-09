package com.jfs.training.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jfs.training.bean.UserBean;
import com.jfs.training.services.UserServiceImpl;

/*
 * REST Controller for user registration.
 * The /register endpoint is deliberately left open (see SecurityConfig)
 * so that a brand new user can create an account before logging in.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * To-Do Item 2.6:
	 *   This method should register a new user submitted from the client.
	 *
	 * TODO:
	 *   --Map the URL to /register using RequestMethod.POST.
	 *   --Perform validation checks (BindingResult).
	 *   --If validation fails, return BAD_REQUEST along with validation error details.
	 *   --On successful validation, invoke the registerUser method of userServiceImpl.
	 *   --Return CREATED status with a success message after successful registration.
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@Valid @ModelAttribute UserBean user, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		// 2. Invoke the service to register the user
		userServiceImpl.registerUser(user);

		// 3. Return a success message with HTTP 201 CREATED status
		return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
	}

	/* Handles all uncaught exceptions */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllExceptions(Exception exception) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
	}
}
