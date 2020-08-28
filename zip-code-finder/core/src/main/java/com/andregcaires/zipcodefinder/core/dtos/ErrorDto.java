package com.andregcaires.zipcodefinder.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDto {

	@JsonProperty("erro")
	private String message;
	
	public ErrorDto(String errorMessage) {
		this.message = errorMessage;
	}
}
