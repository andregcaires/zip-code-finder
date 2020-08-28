package com.andregcaires.zipcodefinder.webapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.andregcaires.zipcodefinder.webapp.resources.ZipCodeResource;

@SpringBootTest
class WebappApplicationTests {

	@Autowired
	private ZipCodeResource zipCodeResource;
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(zipCodeResource);
	}

}
