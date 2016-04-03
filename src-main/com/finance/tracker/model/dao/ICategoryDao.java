package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.User;

public interface ICategoryDao {

	int addCategory(ICategory category);
	
	void updateCategory(ICategory category);

	void removeCategory(ICategory category);
	void removeCategory(int id);

	ICategory foundCategoryByName(String name);

	Category foundById(int id);

	Collection<Category> getAllCategories();

	Collection<Category> getAllCategoriesByUser(User user);
	
}