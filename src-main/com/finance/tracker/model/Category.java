package com.finance.tracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
public class Category implements ICategory {
//	private static final String TAG_CONTAINS_ERROR = "This tag is not in out collection of stacks";
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String name;

	public Category() {
	}

	public Category(String name) throws FinanceTrackerException {
		this();
		setName(name);
	}

	public Category(int id, String name) throws FinanceTrackerException {
		this();
		setName(name);
	}

//	@Override
//	public void addTag(Tag tag) throws FinanceTrackerException {
//		new Validation().validateNotNullObject(tag);
//		synchronized (tags) {
//			tags.add(tag);
//		}
////	}
//
//	@Override
//	public void removeTag(Tag tag) throws FinanceTrackerException {
//		new Validation().validateNotNullObject(tag);
//		if (!tags.contains(tag)) {
//			throw new FinanceTrackerException(TAG_CONTAINS_ERROR);
//		}
//		synchronized (tags) {
//			tags.remove(tag);
//		}
//	}

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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
