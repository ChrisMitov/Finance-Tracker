package com.finance.tracker.model;

import java.util.Date;

import com.finance.tracker.exception.FinanceTrackerException;

public class Income extends FinanceOperation {

	public Income() {
	}

	public Income(int id, int sum, Date date, String description, String photoAddress, Category category,
			RepeatType repeatType, Account account) throws FinanceTrackerException {
		super(id, sum, date, description, photoAddress, category, repeatType, FinanceOperationType.INCOMES,
				account);
	}

}
