package com.finance.tracker.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;


public class Account implements IAccount {
	private static final String OPERATION_CONTAINS_ERROR = "This operation is not valid";
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "balance")
	private int sum;
	@OneToMany
	@Convert
	@JoinColumn(name = "user_id")
	private IUser owner;
	private List<IFinanceOperation> operations;

	public Account() {

	}
	
	public Account(String title, int sum) throws FinanceTrackerException{
		this.setTitle(title);
		this.setSum(sum);
	}

	public Account(String title, int sum, IUser owner) throws FinanceTrackerException {
		this(title,sum);
		this.setOwner(owner);
	}

	public Account(int id, String title, int sum, IUser owner) throws FinanceTrackerException {
		this(title, sum, owner);
		setId(id);
	}

	public IUser getOwner() {
		return owner;
	}

	public void setOwner(IUser owner) {
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

	@Override
	public void addFinanceOperation(IFinanceOperation operation) throws FinanceTrackerException {
		new Validation().validateNotNullObject(operation);
		synchronized (operations) {
			operations.add(operation);
		}
	}

	@Override
	public void removeFinanceOperation(IFinanceOperation operation) throws FinanceTrackerException {
		new Validation().validateNotNullObject(operation);
		if (!operations.contains(operations)) {
			throw new FinanceTrackerException(OPERATION_CONTAINS_ERROR);
		}
		synchronized (operations) {
			operations.remove(operation);
		}
	}

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
		new Validation().validateNegativeNumber(sum);
		this.sum = sum;
	}

}
