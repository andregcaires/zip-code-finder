package com.andregcaires.zipcodefinder.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDto extends BaseDto {

	@JsonProperty("erro")
	private String message;
	
	public ErrorDto(String errorMessage) {
		this.message = errorMessage;
	}

	public String getMessage() {
		return message;
	}	
	
}
