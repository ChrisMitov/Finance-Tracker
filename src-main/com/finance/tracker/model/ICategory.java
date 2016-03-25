package com.finance.tracker.model;

import com.finance.tracker.exception.FinanceTrackerException;

public interface ICategory {

//	void addTag(Tag tag) throws FinanceTrackerException;
//
//	void removeTag(Tag tag) throws FinanceTrackerException;

	int getId();

	void setId(int id) throws FinanceTrackerException;

	String getName();

	void setName(String name) throws FinanceTrackerException;

}