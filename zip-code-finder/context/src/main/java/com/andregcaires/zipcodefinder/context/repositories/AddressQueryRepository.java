package com.andregcaires.zipcodefinder.context.repositories;

import org.springframework.stereotype.Repository;

import com.andregcaires.zipcodefinder.domain.entities.Address;

@Repository
public interface AddressQueryRepository {

	Address findByZipCode(String zipCode);
}
