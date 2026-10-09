package com.jfs.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Central Spring Security configuration.
 * Wires up the custom UserDetailsServiceImpl, defines the password encoder,
 * and configures which endpoints require authentication.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	/**
	 * To-Do Item 2.3:
	 *   Expose a PasswordEncoder bean that the application can use both to
	 *   encode new passwords on registration and to verify passwords on login.
	 *
	 * TODO:
	 *   --Return a new instance of BCryptPasswordEncoder.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Participant has to complete
	}

	/**
	 * To-Do Item 2.4:
	 *   Configure how Spring Security should protect the application's endpoints.
	 *
	 * TODO:
	 *   --Disable CSRF protection (http.csrf().disable()) since this is a
	 *     stateless REST API.
	 *   --Permit unauthenticated access to POST /user/register so new users
	 *     can sign up.
	 *   --Require authentication for every other request (anyRequest().authenticated()).
	 *   --Enable HTTP Basic authentication (httpBasic()) so tools like Postman
	 *     can authenticate using a username and password.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Participant has to complete
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/user/register").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}

	/**
	 * Wires the custom UserDetailsServiceImpl and the PasswordEncoder bean
	 * into Spring Security's authentication process. This part is already
	 * implemented for you as a reference for how AuthenticationManagerBuilder works.
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
}
