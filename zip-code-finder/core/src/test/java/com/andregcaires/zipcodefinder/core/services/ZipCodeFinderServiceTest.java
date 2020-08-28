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

@SpringBootTest
public class ZipCodeFinderServiceTest {

	@Autowired
	private ZipCodeFinderServiceImpl zipCodeFinderService;
	
	@MockBean
	private HttpUtils httpUtils;
	
	@Test
	public void mustFindAddressByZipCode() throws IOException, InterruptedException {
		
		// given
		var validJsonResponse = "{\"cep\": \"14020-525\", \"logradouro\": \"Avenida Presidente Vargas\", \"complemento\": \"de 1701 a 2399 - lado ímpar\", \"bairro\": \"Jardim Santa Ângela\", \"localidade\": \"Ribeirão Preto\", \"uf\": \"SP\", \"ibge\": \"3543402\", \"gia\": \"5824\", \"ddd\": \"16\"}";
		given(httpUtils.httpGet("14020525")).willReturn(validJsonResponse);
		
		// when
		var result = zipCodeFinderService.findAddressByZipCode("14020525");
		
		// then
		AddressDto address = result.getAddress();
		
		Assertions.assertNotNull(address);
		Assertions.assertEquals("14020-525", address.getZipCode());
		Assertions.assertEquals("Avenida Presidente Vargas", address.getStreet());
		Assertions.assertEquals("de 1701 a 2399 - lado ímpar", address.getComplement());
		Assertions.assertEquals("Jardim Santa Ângela", address.getNeighborhood());
		Assertions.assertEquals("Ribeirão Preto", address.getCity());
		Assertions.assertEquals("SP", address.getState());
		Assertions.assertEquals("3543402", address.getIbge());
		Assertions.assertEquals("5824", address.getGia());
		Assertions.assertEquals("16", address.getDdd());	
	}
	
	@Test
	public void mustReturnErrorMessage() throws IOException, InterruptedException {
		
		// given
		var invalidJsonResponse = "{\"erro\": true}";
		given(httpUtils.httpGet("12345678")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGet("12345670")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGet("12345600")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGet("12345000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGet("12340000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGet("12300000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGet("12000000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGet("10000000")).willReturn(invalidJsonResponse);
		given(httpUtils.httpGet("00000000")).willReturn(invalidJsonResponse);
		
		// when
		var result = zipCodeFinderService.findAddressByZipCode("12345678");
		
		// then
		Assertions.assertNotNull(result.getError());
		Assertions.assertEquals("CEP inválido", result.getError().getMessage());
	}
	
	@Test
	public void mustCheckIfResponseBodyIsValid() {
		
		// given
		var validJsonResponse = "{\"cep\": \"14020-525\", \"logradouro\": \"Avenida Presidente Vargas\", \"complemento\": \"de 1701 a 2399 - lado ímpar\", \"bairro\": \"Jardim Santa Ângela\", \"localidade\": \"Ribeirão Preto\", \"uf\": \"SP\", \"ibge\": \"3543402\", \"gia\": \"5824\", \"ddd\": \"16\"}";
		var invalidJsonResponse = "{\"erro\": true}";
		
		// when
		var mustBeTrue = zipCodeFinderService.isValidZipCodeResponse(validJsonResponse);
		var mustBeFalse = zipCodeFinderService.isValidZipCodeResponse(invalidJsonResponse);
		
		// then
		Assertions.assertTrue(mustBeTrue);
		Assertions.assertFalse(mustBeFalse);
	}
	
	@Test
	public void mustSerializeJsonStringIntoAddress() {
		
		// given
		var validJsonResponse = "{\"cep\": \"14020-525\", \"logradouro\": \"Avenida Presidente Vargas\", \"complemento\": \"de 1701 a 2399 - lado ímpar\", \"bairro\": \"Jardim Santa Ângela\", \"localidade\": \"Ribeirão Preto\", \"uf\": \"SP\", \"ibge\": \"3543402\", \"gia\": \"5824\", \"ddd\": \"16\"}";
		AddressDto address = null;
		
		// when
		try {			
			address = zipCodeFinderService.serializeJsonStringIntoAddress(validJsonResponse);
		} catch (JsonProcessingException e) {
			
		}
		
		// then
		Assertions.assertNotNull(address);
		Assertions.assertEquals("14020-525", address.getZipCode());
		Assertions.assertEquals("Avenida Presidente Vargas", address.getStreet());
		Assertions.assertEquals("de 1701 a 2399 - lado ímpar", address.getComplement());
		Assertions.assertEquals("Jardim Santa Ângela", address.getNeighborhood());
		Assertions.assertEquals("Ribeirão Preto", address.getCity());
		Assertions.assertEquals("SP", address.getState());
		Assertions.assertEquals("3543402", address.getIbge());
		Assertions.assertEquals("5824", address.getGia());
		Assertions.assertEquals("16", address.getDdd());		
	}
}
