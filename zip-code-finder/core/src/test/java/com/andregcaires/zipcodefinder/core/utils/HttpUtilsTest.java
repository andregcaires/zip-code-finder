package com.andregcaires.zipcodefinder.core.utils;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pgssoft.httpclient.HttpClientMock;

@SpringBootTest
class HttpUtilsTest {

	@Autowired
	private HttpUtils httpUtils;

	@Test
	void mustGetResponseFromViaCepWebService() throws IOException, InterruptedException {

		// given
		var validJsonResponse = "{\"cep\": \"14020-525\", \"logradouro\": \"Avenida Presidente Vargas\", \"complemento\": \"de 1701 a 2399 - lado ímpar\", \"bairro\": \"Jardim Santa Ângela\", \"localidade\": \"Ribeirão Preto\", \"uf\": \"SP\", \"ibge\": \"3543402\", \"gia\": \"5824\", \"ddd\": \"16\"}";

		HttpClientMock httpClientMock = new HttpClientMock();
		httpClientMock.onGet("https://viacep.com.br/ws/14020525/json/").doReturn(validJsonResponse).doReturnStatus(200);

		// when
		var response = httpUtils.httpGetViaCep("14020525");

		// then
		Assertions.assertEquals(validJsonResponse.contains("Avenida Presidente Vargas"),
				response.contains("Avenida Presidente Vargas"));

	}
}
