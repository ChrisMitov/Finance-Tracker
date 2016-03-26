package com.finance.tracker.model;

import java.time.LocalDate;
import java.util.Date;


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
	
	
}