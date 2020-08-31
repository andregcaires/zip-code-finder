package com.andregcaires.zipcodefinder.core.dtos;

import com.andregcaires.zipcodefinder.domain.entities.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto extends BaseDto {
	
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
	
	public AddressDto() {
		
	}
	
	public AddressDto(Address address) {
		super();
		this.state = address.getState();
		this.city = address.getCity();
		this.neighborhood = address.getNeighborhood();
		this.street = address.getStreet();
		var zipCode = address.getZipCode();
		this.zipCode = zipCode.substring(0, 5) + "-" + zipCode.substring(5, 8);
		
	}
	
	public Address toEntity() {
		
		Address address = new Address();
		
		address.setCity(city);
		address.setNeighborhood(neighborhood);
		address.setState(state);
		address.setStreet(street);
		address.setZipCode(zipCode);
		
		return address;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
}
