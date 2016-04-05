package com.finance.tracker.test;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Collection;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.ICategoryDao;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

public class CategoryTest {
	private static final String UPDATE_CATEGORY_NAME = "Boards";
	private static final String CATEGORY_TEST_NAME = "Shops";
	private ICategoryDao categoryDao = new CategoryDao();

	@Test
	public void addCategory() {

		IUserDAO userDao = new UserDAO();
		IUser user = makeUser();
		userDao.createUser(user);
		ICategory cat = createCategory(user);
		int id = categoryDao.addCategory(cat);
		ICategory category = categoryDao.foundById(id);
		assertEquals(cat.getName(), category.getName());
		categoryDao.removeCategory(cat);
		userDao.deleteUser(user);

	}

	@Test
	public void updateCategory() {
		try {
			IUserDAO userDao = new UserDAO();
			IUser user = makeUser();
			userDao.createUser(user);
			ICategory cat = createCategory(user);
			int id = categoryDao.addCategory(cat);
			cat.setId(id);
			cat.setName(UPDATE_CATEGORY_NAME);
			categoryDao.updateCategory(cat);
			ICategory newCategory = categoryDao.foundById(id);
			assertEquals(cat.getName(), newCategory.getName());
			categoryDao.removeCategory(cat);
			userDao.deleteUser(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void allCategories() {
		Collection<Category> categories = categoryDao.getAllCategories();
		for (ICategory iCategory : categories) {
			System.out.println(iCategory);
		}
		assertNotNull(categories);
	}

	private ICategory createCategory(IUser user) {
		ICategory cat = new Category();
		try {
			cat.setName(CATEGORY_TEST_NAME);
			cat.setUser((User) user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		return cat;
	}

	private IUser makeUser() {
		IUser user = new User();
		user.setFirstName("Luis");
		user.setLastName("Suarez");
		user.setEmail("Luiz@gmail.com");
		try {
			user.setPassword("aqqqW9a");
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		user.setCurrency(Currency.BGN);
		user.setIsAdmin(false);
		return user;
	}
}