package com.finance.tracker.test;

import org.junit.Test;
import static org.junit.Assert.*;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.dao.CategoryDao;

public class CategoryTest {
//	@Test
//	public void addCategory() throws FinanceTrackerException {
//		CategoryDao categoryDao = new CategoryDao();
//		categoryDao.addCategory(new Category("Books"));
//		assertNotNull(categoryDao.foundCategoryByName("Books"));
//	}

	@Test
	public void findCategory() {
		CategoryDao dao = new CategoryDao();
		Category cat = dao.foundById(1);
		System.err.println(cat.getName());
		assertNotNull(cat);
	}
}