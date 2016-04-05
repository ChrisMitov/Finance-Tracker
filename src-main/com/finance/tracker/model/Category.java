package com.finance.tracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
public class Category implements ICategory {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String name;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	public Category() {
	}

	public Category(String name,User user) throws FinanceTrackerException {
		this();
		setName(name);
		setUser(user);
	}

	public Category(int id, String name, User user) throws FinanceTrackerException {
		this(name,user);
		setId(id);
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
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) throws FinanceTrackerException {
		new Validation().validateString(name);
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) throws FinanceTrackerException {
		new Validation().validateNotNullObject(user);
		this.user = user;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
