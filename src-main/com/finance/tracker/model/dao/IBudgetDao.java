package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.RepeatType;

public interface IBudgetDao {

	int addBudget(IBudget budget);

	void updateBudget(IBudget budget);

	void removeBudget(IBudget budget);

	IBudget foundBudgetByTitle(String name);

	Budget foundById(int id);

	Collection<Budget> getAllBudgets();

	Collection<Budget> getAllBudgetsByUser(IUser user);

	String getEndDateById(int id);

	String getStartDateById(int id);

	String getTitleById(int id);

	double getSumById(int id);
	
	RepeatType getTypeById(int id);

	void removeBudgetById(int id);

	
}