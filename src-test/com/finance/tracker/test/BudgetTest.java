package com.finance.tracker.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.model.dao.IAccountDAO;
import com.finance.tracker.model.dao.IBudgetDao;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

public class BudgetTest {
	private static final double DELTA = 1e-15;
	IBudgetDao dao = new BudgetDao();
	IUserDAO userDao = new UserDAO();
	IAccountDAO accountDao = new AccountDAO();
	
	@Test
	public void addBudget() {
		try {
			IUser user = makeNewUser();
			userDao.createUser(user);
			IAccount account = makeNewAccount((User) user);
			accountDao.createAccount(account);
			IBudget budget = makeNewBudget((User) user,(Account) account);
			int id = dao.addBudget(budget);
			IBudget newBudget = dao.foundById(id);
			Collection<Account> accounts = newBudget.getAllAccounts();
			assertEquals(budget.getTitle(), newBudget.getTitle());
			assertEquals(budget.getTotalAmount(), newBudget.getTotalAmount(), DELTA);
			assertEquals(budget.getRepeatType(), newBudget.getRepeatType());
			assertEquals(budget.getUser(), newBudget.getUser());
			assertEquals(budget.getStartDate(), newBudget.getStartDate());
			assertEquals(budget.getEndDate(), newBudget.getEndDate());
			System.err.println(accounts.size());
			assertEquals(budget.getAllAccounts().size(),accounts.size());
			dao.removeBudget(budget);
			accountDao.deleteAccount(account);
			userDao.deleteUser(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
	}

	
	private IBudget makeNewBudget(User user,Account account) throws FinanceTrackerException{
		IBudget budget = new Budget();
		budget.setTitle("Spestoven");
		budget.setStartDate(new Date());
		budget.setRepeatType(RepeatType.MONTHLY);
		budget.setTotalAmount(2000);
		budget.setUser((User) user);
		budget.addAcount(account);
		return budget;
	}
	
	private IUser makeNewUser(){
		IUser user = new User();
		user.setFirstName("Gosho");
		user.setLastName("Petkov");
		user.setEmail("gosho.petkov@gmail.com");
		user.setCurrency(Currency.BGN);
		user.setIsAdmin(false);
		user.setPassword("918jajA");
		return user;
	}
	
	private IAccount makeNewAccount(User user) throws FinanceTrackerException{
		IAccount account = new Account();
		account.setSum(1000);
		account.setOwner(user);
		account.setTitle("Vse taq");
		return account;
	}
	
//	@Test
//	public void getAllBudgetsPerUser() {
//		IUserDAO userDao = new UserDAO();
////		User user = new User();
////		user.setFirstName("Gosho");
////		user.setLastName("Petkov");
////		user.setEmail("gosho.petkov@gmail.com");
////		user.setCurrency(Currency.BGN);
////		user.setIsAdmin(false);
////		user.setPassword("aA@");
////		userDao.createUser(user);
//		
//		assertNotNull(dao.getAllBudgetsByUser(userDao.getUser(10)));
//	}

	// private User createUser() {
	// IUserDAO userDao = new UserDAO();
	// User user = new User();
	// user.setFirstName("Gosho");
	// user.setLastName("Petkov");
	// user.setEmail("gosho.petkov@gmail.com");
	//
	// return userDao.createUser(user);
	//
	// }
}
