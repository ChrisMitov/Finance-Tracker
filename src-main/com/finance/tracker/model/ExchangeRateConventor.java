package com.finance.tracker.model;

import java.util.Collection;
import java.util.Iterator;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.dao.CurrencyDAO;
import com.finance.tracker.model.dao.UserDAO;

public class ExchangeRateConventor {

	private IUser user;
	private Currency newCurrency;
	private double rate;
	private ExchangeRate toCalculate;

	public ExchangeRateConventor(IUser user, Currency newCurrency) {
		this.user = user;
		this.newCurrency = newCurrency;
	}

	public void covert() throws Exception {
		setRate();
		changeAllAccounts();
		changeAllBudgets();
	}

	private void setRate() throws Exception {
		toCalculate = new CurrencyDAO().getRate(newCurrency, user.getCurrency());
		rate = toCalculate.getRate();
		System.out.println(rate+"!!!");
	}

	private void changeAllBudgets() {
		Collection<IBudget> budgets = new UserDAO().getAllBudgetsByUser(user.getUserId());
		for (Iterator<IBudget> it = budgets.iterator(); it.hasNext();) {
			IBudget b = it.next();
			int sum = (int) b.getTotalAmount();
			int newSum = (int) (sum * rate);
			b.setTotalAmount(newSum);
		}
	}

	private void changeAllAccounts() throws FinanceTrackerException {
		Collection<IAccount> accounts = new UserDAO().getAllAccounts(user.getUserId());
		for (Iterator<IAccount> it = accounts.iterator(); it.hasNext();) {
			IAccount b = it.next();
			int sum = (int) b.getSum();
			int newSum = (int) (sum * rate);
			b.setSum(newSum);
		}
	}

}
