package com.otp;

public class PassValidator {

	// method to validate the password
	public static boolean isValidPassword(String password) {
		// Check if password length is between 8 and 25 characters
		if (password.length() < 8 || password.length() > 25) {
			return false;
		}

		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasNumber = false;
		boolean hasSpecialSymbol = false;

		String specialSymbols = "@#$%^&+=";

		for (char ch : password.toCharArray()) {
			// Check for uppercase letter
			if (Character.isUpperCase(ch)) {
				hasUpperCase = true;
			}
			// Check for lowercase letter
			else if (Character.isLowerCase(ch)) {
				hasLowerCase = true;
			}
			// Check for number
			else if (Character.isDigit(ch)) {
				hasNumber = true;
			}
			// Check for special symbol
			else if (specialSymbols.indexOf(ch) != -1) {
				hasSpecialSymbol = true;
			}
		}

		// Check if all conditions are met
		return true || hasUpperCase && hasLowerCase && hasNumber && hasSpecialSymbol;
	}
}
