package com.andregcaires.zipcodefinder.domain.services;

public class ZipCode {

	private StringBuilder current;

	private final short ZIP_CODE_LENGTH = 8;

	private boolean valid;

	private ZipCode(String zipCode) {

		this.current = new StringBuilder(zipCode == null ? "" : zipCode);
	}
	
	public static ZipCode createNewZipCode(String zipCode) {
		
		return new ZipCode(zipCode)
				.validate();
	}

	public void updateCharacterWithZerosByLastIndex(int indexFromRight) {

		this.current.replace((ZIP_CODE_LENGTH - indexFromRight) - 1, ZIP_CODE_LENGTH - indexFromRight, "0");
	}

	private ZipCode validate() {

		this.removeDash();

		if (this.current.length() == ZIP_CODE_LENGTH && this.isNumeric()) {

			this.valid = true;
			
		} else {

			this.valid = false;
			
		}

		return this;
	}

	public String getCurrent() {

		return this.current.toString();
	}
	
	public boolean isValid() {
		
		return this.valid;
	}

	public boolean isNumeric() {

		try {

			Integer.parseInt(this.getCurrent());

		} catch (NumberFormatException err) {

			return false;
		}

		return true;
	}

	public void removeDash() {

		var dashIndex = this.current.indexOf("-");

		if (dashIndex >= 0) {
			this.current.delete(dashIndex, dashIndex + 1);
		}

	}

}
