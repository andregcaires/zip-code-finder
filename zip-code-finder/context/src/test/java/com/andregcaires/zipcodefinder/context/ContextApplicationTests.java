package com.andregcaires.zipcodefinder.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.andregcaires.zipcodefinder.context.repositories.AddressCommandRepository;
import com.andregcaires.zipcodefinder.context.repositories.AddressQueryRepository;

@SpringBootTest
class ContextApplicationTests {
	
	@Autowired
	private AddressCommandRepository commandRepository;
	
	@Autowired
	private AddressQueryRepository queryRepository;

	@Test
	void contextLoads() {
		
		Assertions.assertNotNull(commandRepository);
		Assertions.assertNotNull(queryRepository);
	}

}
