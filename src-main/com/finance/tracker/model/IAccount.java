package com.finance.tracker.model;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IAccount {

	void addFinanceOperation(FinanceOperation operation) throws FinanceTrackerException;

	void removeFinanceOperation(FinanceOperation operation) throws FinanceTrackerException;

	int getId();

	void setId(int id) throws FinanceTrackerException;

	String getTitle();

	void setTitle(String title) throws FinanceTrackerException;

	int getSum();

	void setSum(int sum) throws FinanceTrackerException;
	
	public User getOwner();

	public void setOwner(User owner);

	
}