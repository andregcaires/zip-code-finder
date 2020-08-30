package com.andregcaires.zipcodefinder.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.andregcaires.zipcodefinder.context.repositories.AddressQueryRepository;
import com.andregcaires.zipcodefinder.core.dtos.AddressDto;
import com.andregcaires.zipcodefinder.domain.services.ZipCode;

/*
 * Class implementing the findAddressBySource method, by database
 * */
@Service
@Qualifier("database")
public class ZipCodeFinderServiceByDatabaseImpl extends ZipCodeFinderServiceTemplate implements ZipCodeFinderService {

	Logger logger = LoggerFactory.getLogger(ZipCodeFinderServiceByDatabaseImpl.class);

	@Autowired
	private AddressQueryRepository addressQueryRepository;

	@Override
	void findAddressBySource(ZipCode zipCode) throws Exception {

		var address = addressQueryRepository.findByZipCode(zipCode.toString());

		if (address != null) {

			logger.info("An address has been found at database: {}", address.toString());
			
			result.setAddress(new AddressDto(address));
		}
	}

}
