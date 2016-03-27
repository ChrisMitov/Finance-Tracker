package com.finance.tracker.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
@Table(name = "budget")
public class Budget implements IBudget {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="title")
	private String title;
	@Column(name = "sum")
	private double totalAmount;
//	private double sumPerDay;
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "repeat_type_type")
	private RepeatType repeatType;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
//	@ManyToMany(mappedBy="allBudgets")
//	@ElementCollection
//	private Set<Account> allAccounts = new HashSet<Account>();

	public Budget() {

	}
	
	public Budget(String title, double totalAmount, Date startDate, Date endDate,
			RepeatType repeatType, User user) throws FinanceTrackerException {
		this.setRepeatType(repeatType);
		this.setTotalAmount(totalAmount);
		this.setTitle(title);
//		this.sumPerDay = totalAmount / LocalDate.now().getMonthValue();
		setStartDate(startDate);
		setEndDate(endDate);
		setUser(user);
	}

	public Budget(int id, String title, double totalAmount, Date startDate, Date endDate,
			RepeatType repeatType, User user) throws FinanceTrackerException {
		this(title, totalAmount, startDate, endDate, repeatType, user);
		this.setId(id);
	}

	public void convertMoneyToNewCurrency(Currency newCurrency) {
		this.totalAmount = new ExcangeRate().convertMoney(this.totalAmount, user.getCurrency(), newCurrency);
	}
	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		if (id > 0)
			this.id = id;
	}

//	@Override
//	public void addAcount(Account newAccount) throws FinanceTrackerException {
//		if (newAccount != null) {
//			synchronized (this.allAccounts) {
//				this.allAccounts.add(newAccount);
//			}
//		} else {
//			throw new FinanceTrackerException();
//		}
//	}
//
//	@Override
//	public void removeAccount(Account accountToDelete) throws FinanceTrackerException {
//		if (accountToDelete != null && this.allAccounts.contains(accountToDelete)) {
//			synchronized (allAccounts) {
//				this.allAccounts.remove(accountToDelete);
//			}
//		} else {
//			throw new FinanceTrackerException();
//		}
//	}

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
	public Date getStartDate() {
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
	public Date getEndDate() {
		return endDate;
	}
	@Override
	public void setEndDate(Date endDate) throws FinanceTrackerException {
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
	public void setStartDate(Date startDate) throws FinanceTrackerException {
		new Validation().validateNotNullObject(startDate);
		this.startDate = startDate;
	}

}
