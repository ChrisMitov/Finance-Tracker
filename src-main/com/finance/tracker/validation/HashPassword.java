package com.finance.tracker.validation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
	public String hashPassword(String password) {
		String passwordToHash = password;
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
	
	public boolean isPasswordSecured(String password) {
		if (password != null && password.length() > 0) {
			boolean figure = false;
			boolean capitalLetter = false;
			boolean smallLetter = false;
			for (int index = 0; index < password.length(); index++) {
				char letter = password.charAt(index);
				if (letter >= '0' && letter <= '9') {
					figure = true;
				}
				if (letter >= 'a' && letter <= 'z') {
					smallLetter = true;
				}
				if (letter >= 'A' && letter <= 'Z') {
					capitalLetter = true;
				}
			}
			if (figure && capitalLetter & smallLetter) {
				return true;
			}
		}
		return false;
	}
}
