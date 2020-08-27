package com.andregcaires.zipcodefinder.core.domain;

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
public class ZipCode {

	private StringBuilder current;
	
	public ZipCode(String zipCode) {
		this.current = new StringBuilder(zipCode);
	}
}
