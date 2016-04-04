package com.finance.tracker.view.model;

public class FinanceOperationModel {
	private int sum;
	private String name;
	
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public FinanceOperationModel(int sum, String name) {
		this.sum = sum;
		this.name = name;
	}
	
	
}
