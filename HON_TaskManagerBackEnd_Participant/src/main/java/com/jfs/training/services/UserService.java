package com.jfs.training.services;

import com.jfs.training.bean.UserBean;

/* Defines the business operations related to user registration */
public interface UserService {

	void registerUser(UserBean user) throws Exception;
}
