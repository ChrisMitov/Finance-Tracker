package com.finance.tracker.model;

import java.time.LocalDate;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IBudget {

	void addAcount(IAccount newAccount) throws FinanceTrackerException;

	void removeAccount(IAccount accountToDelete) throws FinanceTrackerException;

	double getTotalAmount();

	void setTotalAmount(double totalAmount);

	String getTitle();

	void setTitle(String title);

	double getSumPerDay();

	LocalDate getStartDate();

	RepeatType getRepeatType();

	void setRepeatType(RepeatType repeatType);
	
	void convertMoneyToNewCurrancy(Currancy newCurrancy);

}