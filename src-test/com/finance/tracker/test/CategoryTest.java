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
	private static final String UPDATE_CATEGORY_NAME = "Motors";
	private static final String CATEGORY_TEST_NAME = "Bike";
	private ICategoryDao categoryDao = new CategoryDao();

//	@Test
//	public void addCategory() {
//		ICategory cat = createCategory();
//		int id = categoryDao.addCategory(cat);
//		ICategory category = categoryDao.foundById(id);
//		assertEquals(cat.getName(), category.getName());
//		categoryDao.removeCategory(cat);
//	}
//
//	@Test
//	public void updateCategory() {
//		try {
//			ICategory cat = createCategory();
//			int id = categoryDao.addCategory(cat);
//			cat.setId(id);
//			cat.setName(UPDATE_CATEGORY_NAME);
//			categoryDao.updateCategory(cat);
//			ICategory newCategory = categoryDao.foundById(id);
//			assertEquals(cat.getName(), newCategory.getName());
//			categoryDao.removeCategory(cat);
//		} catch (FinanceTrackerException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void allCategories() {
		Collection<Category> categories = categoryDao.getAllCategories();
		for (ICategory iCategory : categories) {
			System.out.println(iCategory);
		}
		assertNotNull(categories);
	}


//	private ICategory createCategory() {
//		ICategory cat = new Category();
//		try {
//			cat.setName(CATEGORY_TEST_NAME);
//		} catch (FinanceTrackerException e) {
//			e.printStackTrace();
//		}
//		return cat;
//	}
}