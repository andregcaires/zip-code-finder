package com.andregcaires.zipcodefinder.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.andregcaires.zipcodefinder.core.services.ZipCodeFinderService;
import com.andregcaires.zipcodefinder.core.utils.HttpUtils;

@SpringBootTest
@ContextConfiguration
class CoreApplicationTest {

	@Autowired
	@Qualifier("viaCep")
	private ZipCodeFinderService zipCodeFinderByViaCepService;
	
	@Autowired
	@Qualifier("database")
	private ZipCodeFinderService zipCodeFinderByDatabaseService;
	
	@Autowired
	private HttpUtils httpUtils;
	
	@Test
	void contextLoads() {
		
		Assertions.assertNotNull(zipCodeFinderByViaCepService);
		Assertions.assertNotNull(zipCodeFinderByDatabaseService);
		Assertions.assertNotNull(httpUtils);
	}
}
