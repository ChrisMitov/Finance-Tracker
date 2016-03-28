package com.finance.tracker.test;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.FinanceOperationType;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.Income;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.IAccountDAO;
import com.finance.tracker.model.dao.ICategoryDao;
import com.finance.tracker.model.dao.IFinanceOperationDao;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.TagDao;
import com.finance.tracker.model.dao.UserDAO;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

public class FinanceOperationTest {
	private static final String CATEGORY_TEST_NAME = "Airplane";
	private IFinanceOperationDao foDao = new FinanceOperationDao();
	ICategoryDao categoryDao = new CategoryDao();
	IAccountDAO accountDao = new AccountDAO();
	IUserDAO userDao = new UserDAO();
	
	@Test
	public void addFinanceOperation() throws FinanceTrackerException {
		
		ICategory cat = makeCategory();
		categoryDao.addCategory(cat);
		IUser user = makeUser();
		userDao.createUser(user);
		IAccount account = makeAccount(user);
		accountDao.createAccount(account);
		IFinanceOperation finance = new FinanceOperation();
		finance.setSum(1000);
		finance.setCategory((Category) cat);
		finance.setDescription("Vzeh si novo tv, da zamenq stariq bokluk");
		finance.setDate(new Date());
		finance.setRepeatType(RepeatType.MONTHLY);
		finance.setOperationType(FinanceOperationType.EXPENCES);
		finance.setAccount((Account) account);
		TagDao tags = new TagDao();
		Tag tag = new Tag("Something",(Category) cat);
		tags.addTag(tag);
		finance.addTag(tag);
		int id = foDao.addFinanceOperation(finance);
		FinanceOperation financeOperation = foDao.foundById(id) ;
		assertEquals(finance.getSum(), financeOperation.getSum());
		assertEquals(finance.getDate(), financeOperation.getDate());
		assertEquals(finance.getDescription(), financeOperation.getDescription());
		assertEquals(finance.getOperationType(), financeOperation.getOperationType());
		assertEquals(finance.getAccount(), financeOperation.getAccount());
		assertEquals(finance.getCategory(), financeOperation.getCategory());
		assertEquals(finance.getPhotoAddress(), financeOperation.getPhotoAddress());
		assertEquals(finance.getRepeatType(), financeOperation.getRepeatType());
//		assertEquals(tagove.size(), financeOperation.getAllTags().size());
		foDao.removeFinanceOperation(finance);
		accountDao.deleteAccount(account);
		userDao.deleteUser(user.getUserId());
		tags.removeTag(tag);
		categoryDao.removeCategory(cat);
	}

	@Test
	public void getAllExpences() {
		IAccountDAO dao = new AccountDAO();
		IAccount acc = dao.getAccount(2651);
		Collection<Expense> expense = foDao.getAllExpencesByAccount((Account) acc);
		for (FinanceOperation expenses2 : expense) {
			System.out.println(expenses2.getId());

			System.out.println(expenses2.getSum());
			System.out.println(expenses2.getDescription());
			System.out.println(expenses2.getCategory().getName());
//			System.out.println(expenses2.getAccount().getTitle());
			System.out.println(expenses2.getDate());
		}
		assertNotNull(expense);
	}

	@Test
	public void getAllIncomes() {
		IAccountDAO dao = new AccountDAO();
		IAccount acc = dao.getAccount(2651);
		Collection<Income> income = foDao.getAllIncomeByAccount((Account) acc);
		for (FinanceOperation income2 : income) {
			System.out.println(income2.getId());

			System.out.println(income2.getSum());
			System.out.println(income2.getDescription());
			System.out.println(income2.getCategory().getName());
//			System.out.println(income2.getAccount().getTitle());
			System.out.println(income2.getDate());
		}
		assertNotNull(income);
	}
	
	private ICategory makeCategory() throws FinanceTrackerException {
		ICategory cat = new Category(CATEGORY_TEST_NAME);
		return cat;
	}
	
	private IAccount makeAccount(IUser user) throws FinanceTrackerException{
		IAccount account = new Account();
		account.setTitle("Car parts");
		account.setSum(500);
		account.setOwner((User) user);
		return account;
	}
	
	private IUser makeUser(){
		IUser user = new User();
		user.setFirstName("Luis");
		user.setLastName("Suarez");
		user.setEmail("HAPPY@gmail.com");
		user.setPassword("aqqqW9a");
		user.setCurrency(Currency.BGN);
		user.setIsAdmin(false);
		return user;
	}
}
