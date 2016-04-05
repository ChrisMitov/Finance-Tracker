package com.finance.tracker.model;

import com.finance.tracker.exception.FinanceTrackerException;

public interface ICategory {

	int getId();

	void setId(int id) throws FinanceTrackerException;

	String getName();

	void setName(String name) throws FinanceTrackerException;

	User getUser();

	void setUser(User user) throws FinanceTrackerException;
}