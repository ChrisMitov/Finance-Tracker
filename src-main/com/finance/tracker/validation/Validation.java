package com.finance.tracker.validation;

import com.finance.tracker.exception.FinanceTrackerException;

public class Validation {

	private static final String NEGATIV_NUMBER_ERROR = "Must be positive number!";
	private static final String NULL_OBJECT_ERROR = "Object is null";
	private static final String INCORECT_VALIDATION_STRING = "Incorect string";

	public void validateString(String str) throws FinanceTrackerException {
		validateNotNullObject(str);
		if (str.trim().equals("")) {
			throw new FinanceTrackerException(INCORECT_VALIDATION_STRING);
		}
	}

	public void validateNotNullObject(Object o) throws FinanceTrackerException {
		if (o == null) {
			throw new FinanceTrackerException(NULL_OBJECT_ERROR);
		}
	}

	public void validateNegativeNumber(int number) throws FinanceTrackerException {
		if (number < 0) {
			throw new FinanceTrackerException(NEGATIV_NUMBER_ERROR);
		}
	}
}
