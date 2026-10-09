package com.jfs.training.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jfs.training.Application;

/*
 * Basic sanity test to confirm the Spring application context loads
 * successfully once all the To-Do items have been implemented correctly.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationContextTest {

	@Test
	public void contextLoads() {
		// If this test passes, the application context started without errors.
	}
}
