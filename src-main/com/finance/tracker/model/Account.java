package com.finance.tracker.model;

import javax.persistence.Column;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
public class Account implements IAccount {
	private static final String OPERATION_CONTAINS_ERROR = "This operation is not valid";
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "balance")
	private int sum;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;
	// private Set<FinanceOperation> operations;
	// @OneToMany
	// private List<FinanceOperation> operations;
	// @ManyToMany
	// @ElementCollection
	// @JoinTable(name = "budget_has_account", joinColumns = @JoinColumn(name =
	// "account_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	@ManyToMany(mappedBy = "accounts")
	private List<Budget> allBudgets = new ArrayList<Budget>();

	public Account() {

	}

	public Account(String title, int sum) throws FinanceTrackerException {
		this.setTitle(title);
		this.setSum(sum);
	}

	public Account(String title, int sum, User owner) throws FinanceTrackerException {
		this(title, sum);
		this.setOwner(owner);
	}

	public Account(int id, String title, int sum, User owner) throws FinanceTrackerException {
		this(title, sum, owner);
		setId(id);
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		if (owner != null) {
			this.owner = owner;
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}
	}

	// @Override
	// public void addFinanceOperation(FinanceOperation operation) throws
	// FinanceTrackerException {
	// new Validation().validateNotNullObject(operation);
	// synchronized (operations) {
	// operations.add(operation);
	// }
	// }
	//
	// @Override
	// public void removeFinanceOperation(FinanceOperation operation) throws
	// FinanceTrackerException {
	// new Validation().validateNotNullObject(operation);
	// if (!operations.contains(operations)) {
	// throw new FinanceTrackerException(OPERATION_CONTAINS_ERROR);
	// }
	// synchronized (operations) {
	// operations.remove(operation);
	// }
	// }

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) throws FinanceTrackerException {
		new Validation().validateNegativeNumber(id);
		this.id = id;
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

	@Override
	public int getSum() {
		return sum;
	}

	@Override
	public void setSum(int sum) throws FinanceTrackerException {
		this.sum = sum;
	}

	// public void addBudget(Budget budget) throws FinanceTrackerException {
	// new Validation().validateNotNullObject(budget);
	// synchronized (this.allBudgets) {
	// this.allBudgets.add(budget);
	// }
	// }
	//
	// public void removeBudget(Budget budget) throws FinanceTrackerException {
	// new Validation().validateNotNullObject(budget);
	// if (!allBudgets.contains(budget)) {
	// throw new FinanceTrackerException(OPERATION_CONTAINS_ERROR);
	// }
	// synchronized (budget) {
	// allBudgets.remove(budget);
	// }
	// }

	@Override
	public boolean equals(Object obj) {
		return ((this.getTitle().equals(((Account) obj).getTitle())) && (this.getSum() == ((Account) obj).getSum()));
	}

}
