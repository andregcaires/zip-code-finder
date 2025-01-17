package com.andregcaires.zipcodefinder.core.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Service;

@Service
public class HttpUtilsImpl implements HttpUtils {
	
	private static final String VIACEPURL = "https://viacep.com.br/ws/%s/json/";
	private static final String VIACEPMOCKURL = "https://viacep.com.br/ws/SP/Sao%20Paulo/Ribeirao%20Preto/json/";

	public String httpGetViaCep(String zipCode) throws IOException, InterruptedException {

		var httpClient = HttpClient.newHttpClient();
		
		var url = String.format(VIACEPURL, zipCode);
		
		var request = HttpRequest.newBuilder().uri(URI.create(url)).build();

		var response = httpClient.send(request, BodyHandlers.ofString());

		return response.body();
	}
	
	public String httpGetViaCepForPopulateDatabase() throws IOException, InterruptedException {

		var httpClient = HttpClient.newHttpClient();
		
		var request = HttpRequest.newBuilder().uri(URI.create(VIACEPMOCKURL)).build();

		var response = httpClient.send(request, BodyHandlers.ofString());

		return response.body();
	}
}
