package com.andregcaires.zipcodefinder.context.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.andregcaires.zipcodefinder.domain.entities.Address;

@DataJpaTest
public class AddressCommandRepositoryTest {
	
    @Autowired
    private AddressCommandRepository addressCommandRepository;
    
    @Test
    public void mustInsertNewAddress() {
    	
		// given
		var address = new Address("12345678", "UF", "Cidade", "Bairro", "Rua");
		
		// when
		var result = addressCommandRepository.save(address);
		
		// then
		Assertions.assertNotNull(result);
		Assertions.assertEquals(address, result);
    }
}
