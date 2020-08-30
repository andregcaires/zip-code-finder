package com.andregcaires.zipcodefinder.core.dtos;

import lombok.Getter;
import lombok.Setter;

/*
 * Class containing both the AddressDto in case of successfully finding the address 
 * and the error message to be sent back in case of failure
 * */
@Getter
@Setter
public class ResultDto {

	private AddressDto address;
	private ErrorDto error;
}
