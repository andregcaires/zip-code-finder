package com.andregcaires.zipcodefinder.core.services;

import org.springframework.stereotype.Service;

/*
 * Populates database based in the ViaCep's web service
 * */
@Service
public interface DatabaseService {

	void populateDatabase();
}
