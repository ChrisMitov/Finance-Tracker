package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.Account;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Income;

public interface IFinanceOperationDao {

	int addFinanceOperation(IFinanceOperation operation);

	void removeFinanceOperation(IFinanceOperation operation);

	FinanceOperation foundById(int id);

	Collection<Expense> getAllExpencesByAccount(Account account);

	Collection<Income> getAllIncomeByAccount(Account account);

}