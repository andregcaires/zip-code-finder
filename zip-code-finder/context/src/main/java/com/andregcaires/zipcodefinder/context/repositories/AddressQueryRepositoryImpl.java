package com.andregcaires.zipcodefinder.context.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.andregcaires.zipcodefinder.domain.entities.Address;

@Repository
public class AddressQueryRepositoryImpl implements AddressQueryRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Address findByZipCode(String zipCode) {
		return jdbcTemplate.queryForObject("select * from address where zipcode=?", new Object[] { zipCode },
				new BeanPropertyRowMapper<Address>(Address.class));
	}
}
