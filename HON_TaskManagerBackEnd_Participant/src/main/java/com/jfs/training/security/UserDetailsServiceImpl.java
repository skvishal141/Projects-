package com.jfs.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jfs.training.dao.UserDAO;
import com.jfs.training.entity.UserEntity;

/*
 * Custom UserDetailsService implementation that tells Spring Security how to
 * load a user's credentials and role from the database (via UserDAO) so that
 * it can authenticate incoming requests.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	/**
	 * To-Do Item 2.2:
	 *   This method is called by Spring Security during authentication.
	 *
	 * TODO:
	 *   --Look up the UserEntity by username using userDAO.findByUsername(username).
	 *   --If no user is found, throw a UsernameNotFoundException.
	 *   --If a user is found, build and return a Spring Security User object
	 *     using org.springframework.security.core.userdetails.User.builder(),
	 *     supplying the username, the (already encoded) password, and the role.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userDAO.findByUsername(username);
		if(user==null){
			throw new UsernameNotFoundException(username);
		}

		return User.builder()
				.username(user.getUsername())
				.password(user.getPassword()) // This should be the hashed password from the DB
				.roles(user.getRole())        // Assuming your entity has a getRole() method
				.build(); // Participant has to complete
	}
}
