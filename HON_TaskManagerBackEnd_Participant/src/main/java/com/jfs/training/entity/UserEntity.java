package com.jfs.training.entity;

import javax.persistence.*;

/*
 * Entity class representing the "app_users" table in the database.
 * Stores login credentials and role for a user, used by Spring Security
 * to authenticate and authorize requests.
 */
@Entity
@Table(name = "app_users")
public class UserEntity {

	/* Primary key of the user (auto-generated) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/* Unique login username */
	@Column(unique = true)
	private String username;

	/* BCrypt encoded password. Never store plain text passwords. */
	private String password;

	/* Role of the user, e.g. ROLE_USER or ROLE_ADMIN */
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
