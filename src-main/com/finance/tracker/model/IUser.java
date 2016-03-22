package com.finance.tracker.model;

import java.time.LocalDate;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IUser {

	void addAcount(IAccount newAccount) throws FinanceTrackerException;

	void removeAccount(IAccount accountToDelete) throws FinanceTrackerException;

	IAccount getAccount(String accountTitle) throws FinanceTrackerException;

	void addBudget(IBudget newBudget) throws FinanceTrackerException;

	IBudget getBudget(String budgetTitle) throws FinanceTrackerException;

	void removeBudget(IBudget budgetToDelete) throws FinanceTrackerException;

	String getFirstName();

	String getLastName();

	String getEmail();

	String getPassword();

	Currancy getCurrancy();

	void setCurrancy(Currancy currancy);

	LocalDate getJointedDate();
	
	void convertCurruncy(Currancy newCurrancy);

}