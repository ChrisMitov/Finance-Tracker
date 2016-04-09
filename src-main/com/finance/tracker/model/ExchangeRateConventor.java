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

	public ExchangeRateConventor(IUser user, Currency newCurrency) throws Exception {
		this.user = user;
		this.newCurrency = newCurrency;
		toCalculate = new CurrencyDAO().getRate(newCurrency, user.getCurrency());
		System.out.println(toCalculate.getRate()+"jdvd");
		rate=toCalculate.getRate();
	}

	public void covert() throws Exception {
		changeAllAccounts();
		changeAllBudgets();
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

	public double getRate() {
		return rate;
	}

	public IUser getUser() {
		return user;
	}

	public Currency getNewCurrency() {
		return newCurrency;
	}

	public ExchangeRate getToCalculate() {
		return toCalculate;
	}

}
