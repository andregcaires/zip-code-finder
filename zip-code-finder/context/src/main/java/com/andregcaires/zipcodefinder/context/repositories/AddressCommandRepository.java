package com.andregcaires.zipcodefinder.context.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andregcaires.zipcodefinder.domain.entities.Address;

/*
 * Interface using Spring Data JPA
 * */
@Repository
public interface AddressCommandRepository extends JpaRepository<Address, String> {

}
