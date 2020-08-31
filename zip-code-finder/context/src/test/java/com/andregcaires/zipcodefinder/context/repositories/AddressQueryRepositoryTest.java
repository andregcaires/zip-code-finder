package com.andregcaires.zipcodefinder.context.repositories;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.andregcaires.zipcodefinder.context.repositories.AddressQueryRepository;
import com.andregcaires.zipcodefinder.domain.entities.Address;

@DataJpaTest
public class AddressQueryRepositoryTest {
	
	@Mock
	private AddressQueryRepository addressQueryRepository;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void mustFindObjectAtDatabaseThoughQuery() {

		// given
		var address = new Address("12345678", "UF", "Cidade", "Bairro", "Rua");

		when(jdbcTemplate.queryForObject("select * from address where zipcode=?", new Object[] { "12345678" },
				new BeanPropertyRowMapper<Address>(Address.class))).thenReturn(address);
		
		when(addressQueryRepository.findByZipCode("12345678")).thenReturn(address);
		
		// when
		var result = addressQueryRepository.findByZipCode("12345678");
		
		// then
		Assertions.assertNotNull(result);
	}
}
