package com.finance.tracker.model;

import java.util.Collection;
import java.util.Date;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IBudget {

	void addAccount(Account newAccount) throws FinanceTrackerException;

	void removeAccount(Account accountToDelete) throws FinanceTrackerException;

	Collection<Account> getAllAccounts();
	
	double getTotalAmount();

	void setTotalAmount(double totalAmount);

	String getTitle();

	void setTitle(String title) throws FinanceTrackerException;

//	double getSumPerDay();

	Date getStartDate();

	RepeatType getRepeatType();

	void setRepeatType(RepeatType repeatType);

	int getId();

	void setId(int id);

	void setStartDate(Date startDate) throws FinanceTrackerException;

	Date getEndDate();

	void setEndDate(Date endDate) throws FinanceTrackerException;

	User getUser();

	void setUser(User user);

}