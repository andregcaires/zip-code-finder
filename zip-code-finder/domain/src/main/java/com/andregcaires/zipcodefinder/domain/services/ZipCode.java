package com.andregcaires.zipcodefinder.domain.services;

public class ZipCode {

	private StringBuilder current;

	public static final short ZIPCODELENGTH = 8;

	private boolean valid;

	private ZipCode(String zipCode) {

		this.current = new StringBuilder(zipCode == null ? "" : zipCode);
	}

	/*
	 * Create and validate a ZipCode object, which has a boolean property named
	 * "valid", that indicates if the ZipCode is a valid one without throwing any
	 * exception
	 */
	public static ZipCode createNewZipCode(String zipCode) {

		return new ZipCode(zipCode).validate();
	}

	// updates character to '0' starting from the right
	public void updateCharacterWithZerosByLastIndex(int indexFromRight) {

		this.current.replace((ZIPCODELENGTH - indexFromRight) - 1, ZIPCODELENGTH - indexFromRight, "0");
	}

	// removes '-' from string and checks for valid length and if is numeric
	private ZipCode validate() {

		this.removeDash();

		if (this.current.length() == ZIPCODELENGTH && this.isNumeric()) {

			this.valid = true;

		} else {

			this.valid = false;

		}

		return this;
	}

	// returns the current zip code as string, in order to keep the String Builder
	// immutable outside of the domain object
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
