package com.finance.tracker.model;

import java.time.LocalDate;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IBudget {

	void addAcount(Account newAccount) throws FinanceTrackerException;

	void removeAccount(Account accountToDelete) throws FinanceTrackerException;

	double getTotalAmount();

	void setTotalAmount(double totalAmount);

	String getTitle();

	void setTitle(String title) throws FinanceTrackerException;

//	double getSumPerDay();

	LocalDate getStartDate();

	RepeatType getRepeatType();

	void setRepeatType(RepeatType repeatType);

	void convertMoneyToNewCurrency(Currency newCurrency);

	int getBudgetId();

	void setBudgetId(int budgetId);

	void setStartDate(LocalDate startDate) throws FinanceTrackerException;

	LocalDate getEndDate();

	void setEndDate(LocalDate endDate) throws FinanceTrackerException;

	User getUser();

	void setUser(User user);

}