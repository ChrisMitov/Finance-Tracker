package com.finance.tracker.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
@Table(name = "budget")
public class Budget implements IBudget {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int budgetId;
	@Column(name="title")
	private String title;
	@Column(name = "sum")
	private double totalAmount;
//	private double sumPerDay;
	@Convert
	@Column(name = "start_date")
	private LocalDate startDate;
	@Convert
	@Column(name = "end_date")
	private LocalDate endDate;
	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "repeat_type_id")
	private RepeatType repeatType;
	@ManyToOne
	@JoinColumn(name = "id")
	private User user;
	@ManyToMany(mappedBy="allBudgets")
	private Set<Account> allAccounts = new HashSet<Account>();

	public Budget() {

	}
	
	public Budget(String title, double totalAmount, LocalDate startDate, LocalDate endDate,
			RepeatType repeatType, User user) throws FinanceTrackerException {
		this.setRepeatType(repeatType);
		this.setTotalAmount(totalAmount);
		this.setTitle(title);
//		this.sumPerDay = totalAmount / LocalDate.now().getMonthValue();
		setStartDate(startDate);
		setEndDate(endDate);
		setUser(user);
	}

	public Budget(int id, String title, double totalAmount, LocalDate startDate, LocalDate endDate,
			RepeatType repeatType, User user) throws FinanceTrackerException {
		this(title, totalAmount, startDate, endDate, repeatType, user);
		this.setBudgetId(id);
	}

	public void convertMoneyToNewCurrency(Currency newCurrency) {
		this.totalAmount = new ExcangeRate().convertMoney(this.totalAmount, user.getCurrency(), newCurrency);
	}
	@Override
	public int getBudgetId() {
		return budgetId;
	}
	@Override
	public void setBudgetId(int budgetId) {
		if (budgetId > 0)
			this.budgetId = budgetId;
	}

	@Override
	public void addAcount(Account newAccount) throws FinanceTrackerException {
		if (newAccount != null) {
			synchronized (this.allAccounts) {
				this.allAccounts.add(newAccount);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	@Override
	public void removeAccount(Account accountToDelete) throws FinanceTrackerException {
		if (accountToDelete != null && this.allAccounts.contains(accountToDelete)) {
			synchronized (allAccounts) {
				this.allAccounts.remove(accountToDelete);
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
	public void setTitle(String title) throws FinanceTrackerException {
		new Validation().validateString(title);
		this.title = title;
	}

//	@Override
//	public double getSumPerDay() {
//		return sumPerDay;
//	}

	@Override
	public LocalDate getStartDate() {
		return startDate;
	}

	@Override
	@Enumerated(EnumType.STRING)
	public RepeatType getRepeatType() {
		return repeatType;
	}

	@Override
	@Enumerated(EnumType.STRING)
	public void setRepeatType(RepeatType repeatType) {
		this.repeatType = repeatType;
	}
	@Override
	public LocalDate getEndDate() {
		return endDate;
	}
	@Override
	public void setEndDate(LocalDate endDate) throws FinanceTrackerException {
		new Validation().validateNotNullObject(endDate);
		this.endDate = endDate;
	}
	@Override
	public User getUser() {
		return user;
	}
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public void setStartDate(LocalDate startDate) throws FinanceTrackerException {
		new Validation().validateNotNullObject(startDate);
		this.startDate = startDate;
	}

}
