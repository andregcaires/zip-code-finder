package com.andregcaires.zipcodefinder.core.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.zipcodefinder.context.repositories.AddressCommandRepository;
import com.andregcaires.zipcodefinder.core.dtos.AddressDto;
import com.andregcaires.zipcodefinder.core.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DatabaseServiceImpl implements DatabaseService {
	
	Logger logger = LoggerFactory.getLogger(DatabaseServiceImpl.class);

	@Autowired
	private AddressCommandRepository addressCommandRepository;
	
	@Autowired
	private HttpUtils httpUtils;
	
	public void populateDatabase() {

		var objectMapper = new ObjectMapper();
		
		String response;
		
		try {
			
			response = httpUtils.httpGetViaCepForPopulateDatabase();
			
			if (response != null) {
				
				logger.info("An address list has been found at ViaCep: {}", response);
				
				List<AddressDto> addressDtoList = objectMapper.readValue(response,
						objectMapper.getTypeFactory().constructCollectionType(List.class, AddressDto.class));
				
				addressDtoList.forEach(dto -> addressCommandRepository.save(dto.toEntity()));
			}
			
		} catch (Exception e) {
			
			logger.error("An error has ocurred: {}", e.getMessage());
			e.printStackTrace();
		}
	}
}
