package com.finance.tracker.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

@Entity
@Table(name = "finance_operation")
public class FinanceOperation implements IFinanceOperation {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int sum;
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	private String description;
	@Column(name = "photo")
	private String photoAddress;
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;
	@Enumerated(EnumType.STRING)
	@Column(name = "repeat_type_type")
	private RepeatType repeatType;
	@Enumerated(EnumType.STRING)
	@Column(name = "finance_operation_type_type")
	private FinanceOperationType operationType;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "finance_operation_has_tag", joinColumns = @JoinColumn(name = "finance_operation_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tag;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public FinanceOperation() {
		tag = new HashSet<Tag>();
	}

	public FinanceOperation(int id, int sum, Date date, String description, String photoAddress, Category category,
			RepeatType repeatType, FinanceOperationType operationType) throws FinanceTrackerException {
		this();
		setId(id);
		setSum(sum);
		setDate(date);
		setDescription(description);
		setPhotoAddress(photoAddress);
		setCategory(category);
		setRepeatType(repeatType);
		setOperationType(operationType);
//		setAccount(account);
	}

	@Override
	public void addTag(Tag t) throws FinanceTrackerException {
		new Validation().validateNotNullObject(t);
		synchronized (tag) {
			tag.add(t);
		}
	}

	public Collection<Tag> getAllTags() throws FinanceTrackerException {
		return Collections.unmodifiableCollection(tag);
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
	public int getSum() {
		return sum;
	}

	@Override
	public void setSum(int sum) throws FinanceTrackerException {
		new Validation().validateNegativeNumber(sum);
		this.sum = sum;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) throws FinanceTrackerException {
		new Validation().validateNotNullObject(date);
		this.date = date;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) throws FinanceTrackerException {
		new Validation().validateString(description);
		this.description = description;
	}

	@Override
	public String getPhotoAddress() {
		return photoAddress;
	}

	@Override
	public void setPhotoAddress(String photoAddress) throws FinanceTrackerException {
		this.photoAddress = photoAddress;
	}

	@Override
	public Category getCategory() {
		return category;
	}

	@Override
	public void setCategory(Category category) throws FinanceTrackerException {
		new Validation().validateNotNullObject(category);
		this.category = category;
	}

	@Override
	@Enumerated(EnumType.STRING)
	public RepeatType getRepeatType() {
		return repeatType;
	}

	@Override
	@Enumerated(EnumType.STRING)
	public void setRepeatType(RepeatType repeatType) {
		this.repeatType = repeatType;
	}

	@Override
	@Enumerated(EnumType.STRING)
	public FinanceOperationType getOperationType() {
		return operationType;
	}

	@Override
	@Enumerated(EnumType.STRING)
	public void setOperationType(FinanceOperationType operationType) {
		this.operationType = operationType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result + ((photoAddress == null) ? 0 : photoAddress.hashCode());
		result = prime * result + ((repeatType == null) ? 0 : repeatType.hashCode());
		result = prime * result + sum;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
		FinanceOperation other = (FinanceOperation) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (operationType != other.operationType)
			return false;
		if (photoAddress == null) {
			if (other.photoAddress != null)
				return false;
		} else if (!photoAddress.equals(other.photoAddress))
			return false;
		if (repeatType != other.repeatType)
			return false;
		if (sum != other.sum)
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}

	@Override
	public Account getAccount() {
		return account;
	}

	@Override
	public void setAccount(Account account) throws FinanceTrackerException {
		new Validation().validateNotNullObject(account);
		this.account = account;
	}

}
