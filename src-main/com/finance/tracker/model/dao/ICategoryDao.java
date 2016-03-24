package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;

public interface ICategoryDao {

	void addCategory(ICategory category);

	void removeCategory(int id);

	ICategory foundCategoryByName(String name);

	Category foundById(int id);

	Collection<Category> getAllCategories();

}