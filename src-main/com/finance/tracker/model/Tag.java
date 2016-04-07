package com.finance.tracker.model;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;
<<<<<<< HEAD
import com.finance.tracker.view.model.FinanceOperationModel;
//import com.finance.tracker.view.model.TagModel;
=======
import com.finance.tracker.view.model.TagModel;
>>>>>>> 4325278be5609cda953a56ce87fe6370fdafdaaa

@Entity
public class Tag {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "category_id_category")
	private Category category;
	@ManyToMany(mappedBy = "tag")
	private Set<FinanceOperation> operations;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + id;
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
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

//	public TagModel getViewModelTag(String name) {
//		return new TagModel(name);
//	}
}
