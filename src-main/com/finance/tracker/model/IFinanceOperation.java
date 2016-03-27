package com.finance.tracker.model;

import java.util.Collection;
import java.util.Date;

import com.finance.tracker.exception.FinanceTrackerException;

public interface IFinanceOperation {

	int getId();

	void setId(int id) throws FinanceTrackerException;

	int getSum();

	void setSum(int sum) throws FinanceTrackerException;

	Date getDate();

	void setDate(Date localDate) throws FinanceTrackerException;

	String getDescription();

	void setDescription(String description) throws FinanceTrackerException;

	String getPhotoAddress();

	void setPhotoAddress(String photoAddress) throws FinanceTrackerException;

	Category getCategory();

	void setCategory(Category category) throws FinanceTrackerException;

	RepeatType getRepeatType();

	void setRepeatType(RepeatType repeatType);

	void addTag(Tag tag) throws FinanceTrackerException;

	FinanceOperationType getOperationType();

	void setOperationType(FinanceOperationType operationType);

	Account getAccount();

	void setAccount(Account account) throws FinanceTrackerException;

	Collection<Tag> getAllTags() throws FinanceTrackerException;

}