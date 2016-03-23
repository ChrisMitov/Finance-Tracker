package com.finance.tracker.test;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.dao.CategoryDao;

import junit.framework.TestCase;
public class CategoryTest extends TestCase {
	@Test
	public void addCategory() throws FinanceTrackerException{
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.addCategory(new Category("Cars"));
		assertNotNull(categoryDao.foundCategoryByName("Cars"));
	}
}