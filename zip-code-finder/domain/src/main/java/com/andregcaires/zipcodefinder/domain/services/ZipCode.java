package com.andregcaires.zipcodefinder.domain.services;

public class ZipCode {

	private StringBuilder current;

	public static final short ZIPCODELENGTH = 8;

	private boolean valid;

	private ZipCode(String zipCode) {

		this.current = new StringBuilder(zipCode == null ? "" : zipCode);
	}
	
	public static ZipCode createNewZipCode(String zipCode) {
		
		return new ZipCode(zipCode)
				.validate();
	}

	public void updateCharacterWithZerosByLastIndex(int indexFromRight) {

		this.current.replace((ZIPCODELENGTH - indexFromRight) - 1, ZIPCODELENGTH - indexFromRight, "0");
	}

	private ZipCode validate() {

		this.removeDash();

		if (this.current.length() == ZIPCODELENGTH && this.isNumeric()) {

			this.valid = true;
			
		} else {

			this.valid = false;
			
		}

		return this;
	}

	@Override
	public String toString() {

		return this.current.toString();
	}
	
	public boolean isValid() {
		
		return this.valid;
	}

	private boolean isNumeric() {

		try {

			Integer.parseInt(this.toString());

		} catch (NumberFormatException err) {

			return false;
		}

		return true;
	}

	private void removeDash() {

		var dashIndex = this.current.indexOf("-");

		if (dashIndex >= 0) {
			this.current.delete(dashIndex, dashIndex + 1);
		}

	}

}
