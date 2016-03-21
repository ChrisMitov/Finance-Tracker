package com.finance.tracker.model;

import java.util.HashSet;
import java.util.Set;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

public class Category {
	private static final String TAG_CONTAINS_ERROR = "This tag is not in out collection of stacks";
	private int id;
	private String name;
	private Set<Tag> tags;

	public Category() {
		tags = new HashSet<Tag>();
	}

	public Category(int id, String name) throws FinanceTrackerException {
		super();
		setId(id);
		setName(name);
	}

	public void addTag(Tag tag) throws FinanceTrackerException {
		new Validation().validateNotNullObject(tag);
		synchronized (tags) {
			tags.add(tag);
		}
	}

	public void removeTag(Tag tag) throws FinanceTrackerException {
		new Validation().validateNotNullObject(tag);
		if (!tags.contains(tag)) {
			throw new FinanceTrackerException(TAG_CONTAINS_ERROR);
		}
		synchronized (tags) {
			tags.remove(tag);
		}
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
}
