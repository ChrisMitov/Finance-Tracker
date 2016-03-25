package com.finance.tracker.model;

import java.time.LocalDate;


public interface IUser {

	int getUserId();

	void setUserId(int userId);

	void convertCurrency(Currency newCurrency);
	
	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getEmail();

	void setEmail(String email);

	String getPassword();

	Currency getCurrency();

	void setCurrency(Currency currency);

	LocalDate getJointedDate();

	void setIsAdmin(boolean is);

	boolean isAdmin();
	
	void setStartDate(LocalDate date);
	
	
}