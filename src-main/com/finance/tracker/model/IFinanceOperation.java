package com.finance.tracker.model;

import java.time.LocalDateTime;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IFinanceOperation {

	int getId();

	void setId(int id) throws FinanceTrackerException;

	int getSum();

	void setSum(int sum) throws FinanceTrackerException;

	LocalDateTime getDate();

	void setDate(LocalDateTime date) throws FinanceTrackerException;

	String getDescription();

	void setDescription(String description) throws FinanceTrackerException;

	String getPhotoAddress();

	void setPhotoAddress(String photoAddress) throws FinanceTrackerException;

	Category getCategory();

	void setCategory(Category category) throws FinanceTrackerException;

}