package com.andregcaires.zipcodefinder.core.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Service;

import com.andregcaires.zipcodefinder.core.dtos.Address;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ZipCodeFinderServiceImpl implements ZipCodeFinderService {

	private final String VIA_CEP_URL = "https://viacep.com.br/ws/%s/json/";
	
	private final short ZIP_CODE_LENGTH = 8;
	
	private StringBuilder zipCodeBuilder;

	public Address findAddressByZipCode(String zipCode) throws IOException, InterruptedException {
		
		if (zipCode == null) {
			// TODO return
		}
		
		boolean found = false;
		
		zipCodeBuilder = new StringBuilder(zipCode);

		if (zipCodeBuilder.length() != ZIP_CODE_LENGTH) {

			// TODO return error or notification

		} else {

			// TODO right "zeros" and while loop
			var response = httpGet(String.format(VIA_CEP_URL, zipCode));

			if (isHttpResponseAnErrorMessage(response.body())) {
				
				// TODO notify error or keep on loop
			} else {

				// TODO serialize success response to Address object, gets off loop
				Address address = new ObjectMapper()
						.readValue(response.body(), Address.class);
				
			}
		}

		return null;
	}
	
	public boolean isHttpResponseAnErrorMessage(String responseBody) {
		
		return responseBody.contains("\"erro\": true");
	}
	


	public HttpResponse<String> httpGet(String url) throws IOException, InterruptedException {

		var httpClient = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder().uri(URI.create(url)).build();

		var response = httpClient.send(request, BodyHandlers.ofString());

		return response;
	}
}
