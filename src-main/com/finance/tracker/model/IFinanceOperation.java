package com.finance.tracker.model;

import java.time.LocalDate;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IFinanceOperation {

	int getId();

	void setId(int id) throws FinanceTrackerException;

	int getSum();

	void setSum(int sum) throws FinanceTrackerException;

	LocalDate getDate();

	void setDate(LocalDate localDate) throws FinanceTrackerException;

	String getDescription();

	void setDescription(String description) throws FinanceTrackerException;

	String getPhotoAddress();

	void setPhotoAddress(String photoAddress) throws FinanceTrackerException;

	Category getCategory();

	void setCategory(Category category) throws FinanceTrackerException;

	String getType();

	void setType(String type) throws FinanceTrackerException;

	RepeatType getRepeatType();

	void setRepeatType(RepeatType repeatType);

	void addTag(Tag tag) throws FinanceTrackerException;

	Account getAccount();

	void setAccount(Account account) throws FinanceTrackerException;

}