package com.andregcaires.zipcodefinder.core.services;

import static org.mockito.BDDMockito.given;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.andregcaires.zipcodefinder.core.dtos.AddressDto;
import com.andregcaires.zipcodefinder.core.utils.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootTest
class ZipCodeFinderServiceTest {

	@Autowired
	private ZipCodeFinderServiceByViaCepImpl zipCodeFinderByViaCepService;
	
	@MockBean
	private HttpUtils httpUtils;
	
	@Test
	void mustFindAddressByZipCode() throws IOException, InterruptedException {
		
		// given
		var validJsonResponse = "{\"cep\": \"14020-525\", \"logradouro\": \"Avenida Presidente Vargas\", \"complemento\": \"de 1701 a 2399 - lado ímpar\", \"bairro\": \"Jardim Santa Ângela\", \"localidade\": \"Ribeirão Preto\", \"uf\": \"SP\", \"ibge\": \"3543402\", \"gia\": \"5824\", \"ddd\": \"16\"}";
		given(httpUtils.httpGetViaCep("14020525")).willReturn(validJsonResponse);
		
		// when
		var result = zipCodeFinderByViaCepService.findAddressByZipCode("14020525");
		
		// then
		AddressDto address = result.getAddress();
		
		Assertions.assertNotNull(address);
		Assertions.assertEquals("Avenida Presidente Vargas", address.getStreet());
		Assertions.assertEquals("Jardim Santa Ângela", address.getNeighborhood());
		Assertions.assertEquals("Ribeirão Preto", address.getCity());
		Assertions.assertEquals("SP", address.getState());
	}
	
	@Test
	void mustReturnErrorMessageDueToInvalidZipCode() throws IOException, InterruptedException {
		
		// given
		var invalidJsonResponse = "{\"erro\": true}";
		given(httpUtils.httpGetViaCep("ABC")).willReturn(invalidJsonResponse);
		
		// when
		var result = zipCodeFinderByViaCepService.findAddressByZipCode("ABC");
		
		// then
		Assertions.assertNotNull(result.getError());
		Assertions.assertEquals("CEP inválido", result.getError().getMessage());
	}
	
	@Test
	void mustReturnErrorMessage() throws IOException, InterruptedException {
		
		// given
		var invalidJsonResponse = "{\"erro\": true}";
		given(httpUtils.httpGetViaCep("12345678")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGetViaCep("12345670")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGetViaCep("12345600")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGetViaCep("12345000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGetViaCep("12340000")).willThrow(new IOException());
		given(httpUtils.httpGetViaCep("12300000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGetViaCep("12000000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGetViaCep("10000000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGetViaCep("00000000")).willReturn(invalidJsonResponse);
		
		// when
		var result = zipCodeFinderByViaCepService.findAddressByZipCode("12345678");
		
		// then
		Assertions.assertNotNull(result.getError());
		Assertions.assertEquals("CEP inválido", result.getError().getMessage());
	}
	
	@Test
	void mustCheckIfResponseBodyIsValid() {
		
		// given
		var validJsonResponse = "{\"cep\": \"14020-525\", \"logradouro\": \"Avenida Presidente Vargas\", \"complemento\": \"de 1701 a 2399 - lado ímpar\", \"bairro\": \"Jardim Santa Ângela\", \"localidade\": \"Ribeirão Preto\", \"uf\": \"SP\", \"ibge\": \"3543402\", \"gia\": \"5824\", \"ddd\": \"16\"}";
		var invalidJsonResponse = "{\"erro\": true}";
		
		// when
		var mustBeTrue = zipCodeFinderByViaCepService.isValidZipCodeResponse(validJsonResponse);
		var mustBeFalse = zipCodeFinderByViaCepService.isValidZipCodeResponse(invalidJsonResponse);
		
		// then
		Assertions.assertTrue(mustBeTrue);
		Assertions.assertFalse(mustBeFalse);
	}
	
	@Test
	void mustSerializeJsonStringIntoAddress() throws JsonMappingException, JsonProcessingException {
		
		// given
		var validJsonResponse = "{\"cep\": \"14020-525\", \"logradouro\": \"Avenida Presidente Vargas\", \"complemento\": \"de 1701 a 2399 - lado ímpar\", \"bairro\": \"Jardim Santa Ângela\", \"localidade\": \"Ribeirão Preto\", \"uf\": \"SP\", \"ibge\": \"3543402\", \"gia\": \"5824\", \"ddd\": \"16\"}";
		AddressDto address = null;
		
		// when
		address = zipCodeFinderByViaCepService.serializeJsonStringIntoAddress(validJsonResponse);
		
		// then
		Assertions.assertNotNull(address);
		Assertions.assertEquals("Avenida Presidente Vargas", address.getStreet());
		Assertions.assertEquals("Jardim Santa Ângela", address.getNeighborhood());
		Assertions.assertEquals("Ribeirão Preto", address.getCity());
		Assertions.assertEquals("SP", address.getState());	
	}
}
