package com.andregcaires.zipcodefinder.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZipCodeFinderServiceTest {

	@Autowired
	private ZipCodeFinderService zipCodeFinderService;
	
	@Test
	public void mustUpdateCharacterWithZerosByLastIndex() {
		
		StringBuilder zipCode = new StringBuilder("14020525");
		zipCodeFinderService.updatesCharacterWithZerosByLastIndex(zipCode, 0);
		
		Assertions.assertEquals("14020520", zipCode.toString());
	}
	
	@Test
	public void mustUpdateAllCharactersWithZeros() {
		
		StringBuilder zipCode = new StringBuilder("99999999");
		
		int index = 0;
		final short ZIP_CODE_LENGTH = 8;
		
		while (index != ZIP_CODE_LENGTH) {
			
			zipCodeFinderService.updatesCharacterWithZerosByLastIndex(zipCode, index);
			index++;
		}
		
		Assertions.assertEquals("00000000", zipCode.toString());
	}
}
