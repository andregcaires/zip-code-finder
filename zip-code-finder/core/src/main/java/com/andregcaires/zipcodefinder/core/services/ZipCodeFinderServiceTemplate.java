package com.andregcaires.zipcodefinder.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andregcaires.zipcodefinder.core.dtos.ErrorDto;
import com.andregcaires.zipcodefinder.core.dtos.ResultDto;
import com.andregcaires.zipcodefinder.domain.services.ZipCode;

public abstract class ZipCodeFinderServiceTemplate {
	
	protected ResultDto result;
	
	protected int index;
	
	Logger logger = LoggerFactory.getLogger(ZipCodeFinderServiceTemplate.class);
	
	abstract void findAddressBySource(ZipCode zipCode) throws Exception;

	// this method is not 'final' due to Mockito's limitations
	public ResultDto findAddressByZipCode(String zipCodeString) {

		result = new ResultDto();
		
		var zipCode = ZipCode.createNewZipCode(zipCodeString);

		if (zipCode.isValid()) {

			index = 0;

			while (index != ZipCode.ZIPCODELENGTH) {

				try {

					findAddressBySource(zipCode);

					if (result.getAddress() != null) {

						break;

					} else {

						index++;
					}

				} catch (Exception e) {

					logger.error("An error has ocurred when searching for Zip Code: {} - {}", zipCode, e.getMessage());

					index++;
				}

			}

			if (index == ZipCode.ZIPCODELENGTH) {

				result.setError(new ErrorDto("CEP inválido"));
			}

		} else {

			result.setError(new ErrorDto("CEP inválido"));
		}

		return result;
	}
}
