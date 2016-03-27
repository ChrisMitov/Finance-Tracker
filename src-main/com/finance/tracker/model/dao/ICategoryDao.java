package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;

public interface ICategoryDao {

	int addCategory(ICategory category);
	
	void updateCategory(ICategory category);

	void removeCategory(ICategory category);

	ICategory foundCategoryByName(String name);

	Category foundById(int id);

	Collection<Category> getAllCategories();

}