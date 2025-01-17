package com.andregcaires.zipcodefinder.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.andregcaires.zipcodefinder.core.dtos.AddressDto;
import com.andregcaires.zipcodefinder.core.utils.HttpUtils;
import com.andregcaires.zipcodefinder.domain.services.ZipCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Class implementing the findAddressBySource method, by the ViaCep's web service 
 * */
@Service
@Qualifier("viaCep")
public class ZipCodeFinderServiceByViaCepImpl extends ZipCodeFinderServiceTemplate implements ZipCodeFinderService {

	Logger logger = LoggerFactory.getLogger(ZipCodeFinderServiceByViaCepImpl.class);

	@Autowired
	private HttpUtils httpUtils;

	public void findAddressBySource(ZipCode zipCode) throws Exception {

		var responseBody = httpUtils.httpGetViaCep(zipCode.toString());

		if (isValidZipCodeResponse(responseBody)) {

			logger.info("An address has been found at ViaCep: {}", responseBody);
			
			result.setAddress(serializeJsonStringIntoAddress(responseBody));			
		}
	}


	public boolean isValidZipCodeResponse(String responseBody) {

		return responseBody.contains("localidade") && responseBody.contains("logradouro")
				&& responseBody.contains("bairro") && responseBody.contains("uf");
	}

	public AddressDto serializeJsonStringIntoAddress(String jsonString) throws JsonProcessingException {

		return new ObjectMapper().readValue(jsonString, AddressDto.class);
	}

}
