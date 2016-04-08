package com.finance.tracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.exception.PasswordException;
import com.finance.tracker.validation.Validation;

@Entity
@Table(name = "user")
public class User implements IUser {
	private static final String DEFAULT_ACCOUNT_NAME = "Cash";
	private static final int DEFAULT_START_VALUE = 0;
	private static final Currency DEFAULT_CURRENCY = Currency.BGN;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	// @JoinTable(name = "currency", joinColumns = @JoinColumn(name = "name"))
	@Enumerated(EnumType.STRING)
	@Column(name = "currency_name")
	private Currency currency;
	@Temporal(TemporalType.DATE)
	@Column(name = "start_day")
	private Date jointedDate;
	@Column(name = "is_admin")
	private boolean isAdmin;

	@OneToMany(mappedBy = "user")
	private List<Budget> allBudgets = new ArrayList<Budget>();

	@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
	private List<Account> allAccounts = new ArrayList<Account>();

	public User() {
		super();
	}

	public User(String firstName, String lastName, String password, String email)
			throws PasswordException, FinanceTrackerException {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.isAdmin = false;
		this.currency = DEFAULT_CURRENCY;
		this.jointedDate = new Date();
		allAccounts.add(new Account(DEFAULT_ACCOUNT_NAME, DEFAULT_START_VALUE, this));
	}

	public User(String firstName, String lastName, String email, String password, Currency currency, boolean isAdmin,
			Date date) throws FinanceTrackerException {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setCurrency(currency);
		this.setStartDate(date);
		this.setIsAdmin(isAdmin);
	}

	public User(int id, String firstName, String lastName, String email, String password, Currency currency,
			boolean isAdmin, Date date) throws FinanceTrackerException {
		this(firstName, lastName, email, password, currency, isAdmin, date);
		this.setUserId(id);
	}

	public void setIsAdmin(boolean is) {
		this.isAdmin = is;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	@Override
	public int getUserId() {
		return id;
	}

	@Override
	public void setUserId(int userId) {
		try {
			new Validation().validateNegativeNumber(userId);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		this.id = userId;
	}

	@Override
	public void convertCurrency(Currency newCurrency) {
		this.currency = newCurrency;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		try {
			new Validation().validateString(firstName);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		try {
			new Validation().validateString(lastName);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		this.lastName = lastName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		try {
			new Validation().validateString(email);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		this.email = email;

	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) throws FinanceTrackerException {
		new Validation().validateString(password);
		this.password = password;
	}

	@Enumerated(EnumType.STRING)
	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Enumerated(EnumType.STRING)
	@Override
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public Date getJointedDate() {
		return jointedDate;
	}

	@Override
	public void setStartDate(Date date) {
		try {
			new Validation().validateNotNullObject(date);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		this.jointedDate = date;

	}
	
	
	// public void addAcount(Account newAccount) throws FinanceTrackerException
	// {
	// if (newAccount != null) {
	// synchronized (this.allAccounts) {
	// this.allAccounts.add(newAccount);
	// }
	// } else {
	// throw new FinanceTrackerException();
	// }
	// }
	//
	// public void removeAccount(Account accountToDelete) throws
	// FinanceTrackerException {
	// if (accountToDelete != null &&
	// this.allAccounts.contains(accountToDelete)) {
	// synchronized (allAccounts) {
	// this.allAccounts.remove(accountToDelete);
	// }
	// } else {
	// throw new FinanceTrackerException();
	// }
	// }

	 public void addBudget(IBudget budget) throws FinanceTrackerException {
	 if (budget != null) {
	 synchronized (this.allBudgets) {
	 this.allBudgets.add((Budget) budget);
	 }
	 } else {
	 throw new FinanceTrackerException();
	 }
	 }
	//
	// public void removeBudget(Budget budget) throws FinanceTrackerException {
	// if (budget != null && this.allBudgets.contains(budget)) {
	// synchronized (allBudgets) {
	// this.allBudgets.remove(budget);
	// }
	// } else {
	// throw new FinanceTrackerException();
	// }
	// }

}
