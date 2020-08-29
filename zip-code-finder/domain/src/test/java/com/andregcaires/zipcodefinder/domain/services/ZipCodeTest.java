package com.andregcaires.zipcodefinder.domain.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZipCodeTest {

	@Test
	void mustUpdateCharacterWithZerosByLastIndex() {
		
		// given
		var zipCode = ZipCode.createNewZipCode("14020525");
		
		// when
		zipCode.updateCharacterWithZerosByLastIndex(0);
		
		// then
		Assertions.assertEquals("14020520", zipCode.toString());
	}
	
	@Test
	void mustCreateValidZipCode() {
		
		// given
		String withoutDash = "14020525";
		String withDash = "14020-525";
		
		// when
		var zipCodeWithoutDash = ZipCode.createNewZipCode(withoutDash);
		var zipCodeWithDash = ZipCode.createNewZipCode(withDash);
		
		// then
		Assertions.assertTrue(zipCodeWithoutDash.isValid());
		Assertions.assertTrue(zipCodeWithDash.isValid());
	}
	
	@Test
	void mustCreateInvalidZipCode() {
		
		// given
		String nonNumeric = "ABCDEFGH";
		String longerLength = "140205255";
		String shorterLength = "140205";
		String nullValue = null;
		
		// when
		var zipCodeNonNumeric = ZipCode.createNewZipCode(nonNumeric);
		var zipCodeLongerLength = ZipCode.createNewZipCode(longerLength);
		var zipCodeShorterLength = ZipCode.createNewZipCode(shorterLength);
		var zipCodeNullValue = ZipCode.createNewZipCode(nullValue);
		
		// then
		Assertions.assertFalse(zipCodeNonNumeric.isValid());
		Assertions.assertFalse(zipCodeLongerLength.isValid());
		Assertions.assertFalse(zipCodeShorterLength.isValid());
		Assertions.assertFalse(zipCodeNullValue.isValid());
	}
	
	@Test
	void mustUpdateAllCharactersWithZeros() {
		
		// given
		var zipCode = ZipCode.createNewZipCode("99999999");
		int index = 0;
		final short ZIP_CODE_LENGTH = 8;
		
		// when		
		while (index != ZIP_CODE_LENGTH) {
			
			zipCode.updateCharacterWithZerosByLastIndex(index);
			index++;
		}
		
		// then
		Assertions.assertEquals("00000000", zipCode.toString());
	}
}
