package com.andregcaires.zipcodefinder.core.utils;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface HttpUtils {

	// tries to find the zip code through viacep web service
	String httpGetViaCep(String zipCode) throws IOException, InterruptedException;
	
	// tries to find zip code in the current database
	String httpGetViaCepForPopulateDatabase() throws IOException, InterruptedException;
}
