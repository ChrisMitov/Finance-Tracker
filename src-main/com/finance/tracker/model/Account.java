package com.finance.tracker.model;

import java.util.List;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

public class Account implements IAccount {
	private static final String OPERATION_CONTAINS_ERROR = "This operation is not valid";
	private int id;
	private String title;
	private int sum;
	private List<IFinanceOperation> operations;

	@Override
	public void addFinanceOperation(IFinanceOperation operation) throws FinanceTrackerException {
		new Validation().validateNotNullObject(operation);
		synchronized (operations) {
			operations.add(operation);
		}
	}

	@Override
	public void removeFinanceOperation(IFinanceOperation operation) throws FinanceTrackerException {
		new Validation().validateNotNullObject(operation);
		if (!operations.contains(operations)) {
			throw new FinanceTrackerException(OPERATION_CONTAINS_ERROR);
		}
		synchronized (operations) {
			operations.remove(operation);
		}
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) throws FinanceTrackerException {
		new Validation().validateNegativeNumber(id);
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) throws FinanceTrackerException {
		new Validation().validateString(title);
		this.title = title;
	}

	@Override
	public int getSum() {
		return sum;
	}

	@Override
	public void setSum(int sum) throws FinanceTrackerException {
		new Validation().validateNegativeNumber(sum);
		this.sum = sum;
	}

}
