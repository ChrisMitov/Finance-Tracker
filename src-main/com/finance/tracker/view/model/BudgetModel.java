package com.finance.tracker.view.model;

public class BudgetModel {
	private String title;
	private int sum;
	
	public BudgetModel(String title, int sum) {
		super();
		this.title = title;
		this.sum = sum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
