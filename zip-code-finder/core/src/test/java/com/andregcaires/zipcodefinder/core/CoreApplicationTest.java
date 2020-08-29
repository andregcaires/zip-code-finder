package com.andregcaires.zipcodefinder.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.andregcaires.zipcodefinder.core.services.ZipCodeFinderService;
import com.andregcaires.zipcodefinder.core.utils.HttpUtils;

@SpringBootTest
class CoreApplicationTest {

	@Autowired
	private ZipCodeFinderService zipCodeFinderService;
	
	@Autowired
	private HttpUtils httpUtils;
	
	@Test
	void contextLoads() {
		
		Assertions.assertNotNull(zipCodeFinderService);
		Assertions.assertNotNull(httpUtils);
	}
}
