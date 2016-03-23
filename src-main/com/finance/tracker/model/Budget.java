package com.finance.tracker.model;

import java.time.LocalDate;
import java.util.List;

import com.finance.tracker.exception.FinanceTrackerException;

public class Budget implements IBudget {

	private int budgetId;
	private String title;
	private double totalAmount;
	private double sumPerDay;
	private LocalDate startDate;
	private RepeatType repeatType;
	private List<IAccount> accounts;
	private IUser user;

	public Budget(int id, String title, double totalAmount, RepeatType repeatType) {
		this.setBudgetId(id);
		this.setRepeatType(repeatType);
		this.setTotalAmount(totalAmount);
		this.setTitle(title);
		this.startDate = LocalDate.now();
		this.sumPerDay = totalAmount / LocalDate.now().getMonthValue();
	}

	public void convertMoneyToNewCurrency(Currency newCurrency) {
		this.totalAmount = new ExcangeRate().convertMoney(this.totalAmount, user.getCurrency(), newCurrency);
	}

	public int getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(int budgetId) {
		if (budgetId > 0)
			this.budgetId = budgetId;
	}

	@Override
	public void addAcount(IAccount newAccount) throws FinanceTrackerException {
		if (newAccount != null) {
			synchronized (this.accounts) {
				this.accounts.add(newAccount);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	@Override
	public void removeAccount(IAccount accountToDelete) throws FinanceTrackerException {
		if (accountToDelete != null && this.accounts.contains(accountToDelete)) {
			synchronized (accounts) {
				this.accounts.remove(accountToDelete);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	@Override
	public double getTotalAmount() {
		return totalAmount;
	}

	@Override
	public void setTotalAmount(double totalAmount) {
		if (totalAmount > 0)
			this.totalAmount = totalAmount;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		if (title != null && title.length() > 0)
			this.title = title;
	}

	@Override
	public double getSumPerDay() {
		return sumPerDay;
	}

	@Override
	public LocalDate getStartDate() {
		return startDate;
	}

	@Override
	public RepeatType getRepeatType() {
		return repeatType;
	}

	@Override
	public void setRepeatType(RepeatType repeatType) {
		this.repeatType = repeatType;
	}

}
