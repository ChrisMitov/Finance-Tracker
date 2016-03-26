package com.finance.tracker.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collection;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.ICategoryDao;

public class CategoryTest {
	private ICategoryDao categoryDao = new CategoryDao();

	@Test
	public void addCategory() throws FinanceTrackerException {
		categoryDao.addCategory(new Category("Cars"));
		assertNotNull(categoryDao.foundCategoryByName("Cars"));
		
	}

	@Test
	public void findCategory() {
		Category cat = categoryDao.foundById(351);
		assertNotNull(cat);
	}

	@Test
	public void allCategories() {
		Collection<Category> categories = categoryDao.getAllCategories();
		for (ICategory iCategory : categories) {
			System.out.println(iCategory);
		}
		assertNotNull(categories);
	}

	@Test
	public void removeCategory() {
		Collection<Category> categories = categoryDao.getAllCategories();
		int id = 1;
		for (ICategory iCategory : categories) {
			System.out.println(iCategory);
			id = iCategory.getId();
		}
		categoryDao.removeCategory(id);
		assertNull(categoryDao.foundById(id));
	}
}