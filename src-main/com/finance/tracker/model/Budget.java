package com.finance.tracker.model;

import java.time.LocalDate;
import java.util.List;

import com.finance.tracker.exception.FinanceTrackerException;

public class Budget implements IBudget {

	private String title;
	private double totalAmount;
	private double sumPerDay;
	private LocalDate startDate;
	private RepeatType repeatType;
	private List<IAccount> accounts;
	private User user;
	
	public Budget(String title, double totalAmount, RepeatType repeatType) {
		this.setRepeatType(repeatType);
		this.setTotalAmount(totalAmount);
		this.setTitle(title);
		this.startDate = LocalDate.now();
		this.sumPerDay=totalAmount/LocalDate.now().getMonthValue();
	}
	
	public void convertMoneyToNewCurrancy(Currancy newCurrancy){
		this.totalAmount = new ExcangeRate().convertMoney(this.totalAmount, user.getCurrancy(), newCurrancy);
	}
	
	@Override
	public void addAcount(IAccount newAccount) throws FinanceTrackerException {
		if(newAccount!=null){
			synchronized (this.accounts) {
				this.accounts.add(newAccount);
			}
		}
		else{
			throw new FinanceTrackerException();
		}
	}

	
	@Override
	public void removeAccount(IAccount accountToDelete) throws FinanceTrackerException{
		if(accountToDelete!=null && this.accounts.contains(accountToDelete)){
			synchronized (accounts) {
				this.accounts.remove(accountToDelete);
			}
		}
		else{
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
