package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IBudget;

public interface IBudgetDao {

	void addBudget(IBudget budget);

	void removeBudget(int id);

	IBudget foundBudgetByName(String name);

	Budget foundById(int id);

	Collection<Budget> getAllBudgets();

}