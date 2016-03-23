package com.finance.tracker.model;

import java.time.LocalDate;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IUser {

	int getUserId();

	void setUserId(int userId);

	void convertCurrency(Currency newCurrency);

	void addAcount(IAccount newAccount) throws FinanceTrackerException;

	void removeAccount(IAccount accountToDelete) throws FinanceTrackerException;

	IAccount getAccount(String accountTitle) throws FinanceTrackerException;

	void addBudget(IBudget newBudget) throws FinanceTrackerException;

	IBudget getBudget(String budgetTitle) throws FinanceTrackerException;

	void removeBudget(IBudget budgetToDelete) throws FinanceTrackerException;

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

}