package com.finance.tracker.model;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
@Table(name = "budget")
public class Budget implements IBudget {
	private static final int PLUS_ONE_DAY = 1 * 24 * 60 * 60 * 100;
	private static final int PLUS_ONE_WEEK = 7 * 24 * 60 * 60 * 100;
	private static final int PLUS_ONE_MONTH = 30 * 24 * 60 * 60 * 100;
	private static final int PLUS_ONE_YEAR = 356 * 24 * 60 * 60 * 100;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "title")
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
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "budget_has_account", joinColumns = @JoinColumn(name = "budget_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
	private List<Account> accounts;
	// @ManyToMany(mappedBy="allBudgets")
	// @ElementCollection
	// private Set<Account> allAccounts = new HashSet<Account>();

	public Budget() {
		this.accounts = new ArrayList<>();
	}

	public Budget(String title, double sum, User user) throws FinanceTrackerException {
		this();
		this.setTitle(title);
		this.setTotalAmount(sum);
		this.setUser(user);
	}

	public Budget(String title, double totalAmount, Date startDate, RepeatType repeatType, User user)
			throws FinanceTrackerException {
		this.setRepeatType(repeatType);
		this.setTotalAmount(totalAmount);
		this.setTitle(title);
//		sumPerDay = totalAmount / LocalDate.now().getMonthValue();
		setStartDate(startDate);
		generateEndDate(repeatType);
		setUser(user);
	}

	public Budget(int id, String title, double totalAmount, Date startDate, Date endDate, RepeatType repeatType,
			User user) throws FinanceTrackerException {
		this(title, totalAmount, startDate, repeatType, user);
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

	@Override
	public void addAcount(Account newAccount) throws FinanceTrackerException {
		if (newAccount != null) {
			synchronized (this.accounts) {
				this.accounts.add(newAccount);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	@Override
	public void removeAccount(Account accountToDelete) throws FinanceTrackerException {
		if (accountToDelete != null && this.accounts.contains(accountToDelete)) {
			synchronized (accounts) {
				this.accounts.remove(accountToDelete);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	public Collection<Account> getAllAccounts() {
		return Collections.unmodifiableCollection(accounts);
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

	// @Override
	// public double getSumPerDay() {
	// return sumPerDay;
	// }

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

	private void generateEndDate(RepeatType type) {
		if (type.equals(RepeatType.DAILY)) {
			endDate = new Date(startDate.getTime() + PLUS_ONE_DAY);
		}
		if (type.equals(RepeatType.MONTHLY)) {
			endDate = new Date(startDate.getTime() + PLUS_ONE_MONTH);
		}
		if (type.equals(RepeatType.NO_REPEAT)) {
			// to be added
		}
		if (type.equals(RepeatType.WEEKLY)) {
			endDate = new Date(startDate.getTime() + PLUS_ONE_WEEK);
		}
		if (type.equals(RepeatType.YEARLY)) {
			endDate = new Date(startDate.getTime() + PLUS_ONE_YEAR);
		}
	}
	
//	public double getSumPerDay(){
//		return this.sumPerDay;
//	}

}
