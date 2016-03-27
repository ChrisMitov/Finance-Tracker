package com.finance.tracker.model;

import java.util.Date;

import com.finance.tracker.exception.FinanceTrackerException;

public class Expense extends FinanceOperation {

	public Expense() {
	}

	public Expense(int id, int sum, Date date, String description, String photoAddress, Category category,
			RepeatType repeatType) throws FinanceTrackerException {
		super(id, sum, date, description, photoAddress, category, repeatType, FinanceOperationType.EXPENCES);
	}

}
