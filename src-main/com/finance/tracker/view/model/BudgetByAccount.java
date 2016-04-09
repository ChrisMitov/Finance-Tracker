package com.finance.tracker.view.model;

public class BudgetByAccount {
	String account;
	int times;

	public BudgetByAccount(String account, int times) {
		super();
		this.account = account;
		this.times = times;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
