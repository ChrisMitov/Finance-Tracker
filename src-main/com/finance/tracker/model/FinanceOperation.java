package com.finance.tracker.model;

import java.time.LocalDateTime;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.validation.Validation;

public class FinanceOperation implements IFinanceOperation {
	private int id;
	private int sum;
	private LocalDateTime date;
	private String description;
	private String photoAddress;
	private ICategory category;
	private String type;

	public FinanceOperation(int id, int sum, LocalDateTime date, String description, String photoAddress,
			ICategory category,String type ) throws FinanceTrackerException {
		setId(id);
		setSum(sum);
		setDate(date);
		setDescription(description);
		setPhotoAddress(photoAddress);
		setCategory(category);
		setType(type);
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
	public LocalDateTime getDate() {
		return date;
	}

	@Override
	public void setDate(LocalDateTime date) throws FinanceTrackerException {
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
	public ICategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(ICategory category) throws FinanceTrackerException {
		new Validation().validateNotNullObject(category);
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) throws FinanceTrackerException {
		new Validation().validateString(type);
		this.type = type;
	}

}
