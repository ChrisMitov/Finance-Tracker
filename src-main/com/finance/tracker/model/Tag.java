package com.finance.tracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
public class Tag {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@ManyToOne
	private Category category;

	public Tag() {
	}


	public Tag(String name, Category category) throws FinanceTrackerException {
		setName(name);
		setCategory(category);
	}

	public Tag(int id, String name, Category category) throws FinanceTrackerException {
		this(name, category);
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) throws FinanceTrackerException {
		new Validation().validateNegativeNumber(id);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws FinanceTrackerException {
		new Validation().validateString(name);
		this.name = name;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) throws FinanceTrackerException {
		new Validation().validateNotNullObject(category);
		this.category = category;
	}
}
