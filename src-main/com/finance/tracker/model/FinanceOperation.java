package com.finance.tracker.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

//@Entity
@Table(name = "finance_operation")
public class FinanceOperation implements IFinanceOperation {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int sum;
	@Convert
	private LocalDate date;
	private String description;
	@Column(name = "photo")
	private String photoAddress;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	private String type;
	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "repeat_type_idrepeat_type")
	private RepeatType repeatType;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "finance_operation_has_tag", joinColumns = { @JoinColumn(name = "finance_operation_id"),
			@JoinColumn(name = "tag_id") })
	private Set<Tag> tags;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id_account")
	private Account account;

	public FinanceOperation() {
		tags = new HashSet<Tag>();
	}

	public FinanceOperation(int id, int sum, LocalDate date, String description, String photoAddress, Category category,
			String type, RepeatType repeatType) throws FinanceTrackerException {
		this();
		setId(id);
		setSum(sum);
		setDate(date);
		setDescription(description);
		setPhotoAddress(photoAddress);
		setCategory(category);
		setType(type);
		setRepeatType(repeatType);
	}

	public void addTag(Tag tag) throws FinanceTrackerException {
		new Validation().validateNotNullObject(tag);
		synchronized (tag) {
			tags.add(tag);
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
	public int getSum() {
		return sum;
	}

	@Override
	public void setSum(int sum) throws FinanceTrackerException {
		new Validation().validateNegativeNumber(sum);
		this.sum = sum;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public void setDate(LocalDate date) throws FinanceTrackerException {
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
		new Validation().validateString(photoAddress);
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
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) throws FinanceTrackerException {
		new Validation().validateString(type);
		this.type = type;
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
	public Account getAccount() {
		return account;
	}

	@Override
	public void setAccount(Account account) throws FinanceTrackerException {
		new Validation().validateNotNullObject(account);
		this.account = account;
	}

}
