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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
