package com.finance.tracker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
@Table
public class Category implements ICategory {
	private static final String TAG_CONTAINS_ERROR = "This tag is not in out collection of stacks";
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Tag> tags;

	public Category() {
		tags = new HashSet<Tag>();
	}

	public Category(int id, String name) throws FinanceTrackerException {
		super();
		setId(id);
		setName(name);
	}

	@Override
	public void addTag(Tag tag) throws FinanceTrackerException {
		new Validation().validateNotNullObject(tag);
		synchronized (tags) {
			tags.add(tag);
		}
	}

	@Override
	public void removeTag(Tag tag) throws FinanceTrackerException {
		new Validation().validateNotNullObject(tag);
		if (!tags.contains(tag)) {
			throw new FinanceTrackerException(TAG_CONTAINS_ERROR);
		}
		synchronized (tags) {
			tags.remove(tag);
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
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) throws FinanceTrackerException {
		new Validation().validateString(name);
		this.name = name;
	}
}
