package com.andregcaires.zipcodefinder.core.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.zipcodefinder.core.dtos.AddressDto;
import com.andregcaires.zipcodefinder.core.dtos.ErrorDto;
import com.andregcaires.zipcodefinder.core.dtos.ResultDto;
import com.andregcaires.zipcodefinder.core.utils.HttpUtils;
import com.andregcaires.zipcodefinder.domain.services.ZipCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ZipCodeFinderServiceImpl implements ZipCodeFinderService {

	private final short ZIP_CODE_LENGTH = 8;

	Logger logger = LoggerFactory.getLogger(ZipCodeFinderServiceImpl.class);
	
	@Autowired
	private HttpUtils httpUtils;

	public ResultDto findAddressByZipCode(String zipCodeString) {

		var zipCode = ZipCode.createNewZipCode(zipCodeString);

		var result = new ResultDto();

		if (zipCode.isValid()) {

			int index = 0;
			String responseBody;

			while (index != ZIP_CODE_LENGTH) {

				try {

					responseBody = httpUtils.httpGetViaCep(zipCode.getCurrent());

					if (!isValidZipCodeResponse(responseBody)) {

						zipCode.updateCharacterWithZerosByLastIndex(index);
						index++;

					} else {

						logger.info("An address has been found: " + responseBody);

						result.setAddress(serializeJsonStringIntoAddress(responseBody));
						
						break;
					}

				} catch (IOException | InterruptedException e) {

					logger.error("An error has ocurred when searching for Zip Code: " + zipCode.getCurrent() + " - "
							+ e.getMessage());

					index++;
				}

			}

			if (index == ZIP_CODE_LENGTH) {

				result.setError(new ErrorDto("CEP inválido"));
			}
			
		} else {
			
			result.setError(new ErrorDto("CEP inválido"));
		}

		return result;
	}

	public boolean isValidZipCodeResponse(String responseBody) {
		
		return responseBody.contains("localidade") && responseBody.contains("logradouro") && responseBody.contains("bairro")
				&& responseBody.contains("uf");
	}

	public AddressDto serializeJsonStringIntoAddress(String jsonString)
			throws JsonMappingException, JsonProcessingException {

		return new ObjectMapper().readValue(jsonString, AddressDto.class);
	}

}
