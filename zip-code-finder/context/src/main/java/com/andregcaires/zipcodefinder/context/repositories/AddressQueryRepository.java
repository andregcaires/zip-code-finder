package com.andregcaires.zipcodefinder.context.repositories;

import org.springframework.stereotype.Repository;

import com.andregcaires.zipcodefinder.domain.entities.Address;

/*
 * Its implementation uses the Spring Data JDBC to search for Addresses in database, due to better performance
 * */
@Repository
public interface AddressQueryRepository {

	Address findByZipCode(String zipCode);
}
