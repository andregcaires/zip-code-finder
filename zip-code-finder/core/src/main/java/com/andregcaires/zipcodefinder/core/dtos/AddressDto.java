package com.andregcaires.zipcodefinder.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AddressDto {
	
	@JsonProperty("uf")
	private String state;
	
	@JsonProperty("localidade")
	private String city;
	
	@JsonProperty("bairro")
	private String neighborhood;
	
	@JsonProperty("logradouro")
	private String street;
	
	@JsonProperty("cep")
	private String zipCode;
	
	@JsonProperty("complemento")
	private String complement;
	
	@JsonProperty("ibge")
	private String ibge;
	
	@JsonProperty("gia")
	private String gia;
	
	@JsonProperty("ddd")
	private String ddd;

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

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	
	
}
