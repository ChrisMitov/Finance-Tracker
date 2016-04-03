package com.finance.tracker.model;

import java.util.Date;

import com.finance.tracker.exception.PasswordException;


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

	Date getJointedDate();

	void setIsAdmin(boolean is);

	boolean isAdmin();
	
	void setStartDate(Date date);
	
	void setPassword(String password) throws PasswordException;
	
}