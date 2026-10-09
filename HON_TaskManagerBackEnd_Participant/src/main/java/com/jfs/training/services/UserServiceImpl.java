package com.jfs.training.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jfs.training.bean.UserBean;
import com.jfs.training.dao.UserDAO;
import com.jfs.training.entity.UserEntity;

/*
 * This class handles new user registration, making sure the password is
 * encoded before it is persisted to the database.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * To-Do Item 2.5:
	 *   This method should register a new user.
	 *
	 * TODO:
	 *   --Create a new UserEntity and copy the username from the UserBean.
	 *   --Encode the plain text password using passwordEncoder.encode(...)
	 *     before setting it on the entity. Never store plain text passwords.
	 *   --Set a default role of "ROLE_USER" on the entity.
	 *   --Save the entity using userDAO.
	 */
	@Override
	public void registerUser(UserBean user) throws Exception {
		// Participant has to complete
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity.setRole("ROLE_USER");
		userDAO.save(userEntity);
	}
}
