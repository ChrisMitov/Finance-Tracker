package com.finance.tracker.view.model;

public class ExpenseModel {
	private String day;
	private int sum;
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public ExpenseModel(String day, int sum) {
		this.day = day;
		this.sum = sum;
	}
	
	
}
