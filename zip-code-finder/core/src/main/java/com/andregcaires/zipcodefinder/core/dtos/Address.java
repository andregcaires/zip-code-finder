package com.andregcaires.zipcodefinder.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@JsonProperty("cep")
	private String zipCode;
	
	@JsonProperty("uf")
	private String state;
	
	@JsonProperty("localidade")
	private String city;
	
	@JsonProperty("bairro")
	private String neighborhood;
	
	@JsonProperty("logradouro")
	private String street;
}
