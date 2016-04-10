package com.finance.tracker.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.exception.PasswordException;
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
	private static final int ACCOUNT_SUM = 1000;
	private static final int NEW_ACCOUNT_SUM = 5000;
	private static final String NEW_ACCOUNT_NAME = "Goods";
	private static final String NEW_USER_PASSWORD = "aA12";
	private static final String NEW_USER_EMAIL = "Kenobi112@gmail.com";
	private static final String NEW_USER_LAST_NAME = "Kenobi";
	private static final String NEW_USER_FIRST_NAME = "Obi-One";
	private static final int UPDATE_SUM = 100;
	private static final String ACCOUNT_TITLE = "Cars";
	private static final int BUDGET_SUM = 2000;
	private static final String BUDGET_TITLE = "Spestoven";
	private static final String USER_PASSWORD = "918jajA";
	private static final String USER_EMAIL = "pff2322@gmail.com";
	private static final String USER_LASTNAME = "Petkov";
	private static final String USER_NAME = "Gosho";
	private static final String UPDATE_TITLE = "Mesechen";
	private static final double DELTA = 1e-15;
	IBudgetDao dao = new BudgetDao();
	IUserDAO userDao = new UserDAO();
	IAccountDAO accountDao = new AccountDAO();

	// @Test
	// public void testBudgetAccountRelation() throws FinanceTrackerException {
	// System.out.println("Test 1");
	// Account a = new Account("Purvi", 20, new UserDAO().getUser(3051));
	// Account c = new Account("Vtori", 20, new UserDAO().getUser(3051));
	// new AccountDAO().createAccount(a);
	// new AccountDAO().createAccount(c);
	// Budget b = new Budget("Testov2", 120, new Date(), new Date(),new
	// UserDAO().getUser(3051));
	// b.addAccount(a);
	// b.addAccount(c);
	// new BudgetDao().addBudget(b);
	// Collection<Account>accs = b.getAllAccounts();
	// System.out.println("Before");
	// for(Account acc:accs){
	// System.out.println(acc.getTitle());
	// }
	// b.deleteAllAccounts();
	// Account d = new Account("Nov", 20, new UserDAO().getUser(3051));
	// b.addAccount(d);
	// new BudgetDao().updateBudget(b);
	// Collection<Account>accounts = b.getAllAccounts();
	// System.out.println("After");
	// for(Account acc:accounts){
	// System.out.println(acc.getTitle());
	// }
	// }

	@Test
	public void addBudget() {
		try {
			IUser user = makeNewUser();
			IAccount account = makeNewAccount((User) user);
			userDao.createUser(user);
			accountDao.createAccount(account);
			IBudget budget = makeNewBudget((User) user, (Account) account);
			int id = dao.addBudget(budget);
			IBudget newBudget = dao.foundById(id);
			Collection<Account> accounts = newBudget.getAllAccounts();
			assertEquals(budget.getTitle(), newBudget.getTitle());
			assertEquals(budget.getTotalAmount(), newBudget.getTotalAmount(), DELTA);
			assertEquals(budget.getRepeatType(), newBudget.getRepeatType());
			assertEquals(budget.getStartDate(), newBudget.getStartDate());
			assertEquals(budget.getEndDate(), newBudget.getEndDate());
			assertEquals(budget.getAllAccounts().size(), accounts.size());
			dao.removeBudget(budget);
			accountDao.deleteAccount(account);
			userDao.deleteUser(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public void updateBudget() throws PasswordException {
//
//		try {
//			IUser user = makeNewUser();
//			userDao.createUser(user);
//			IAccount account = makeNewAccount((User) user);
//			accountDao.createAccount(account);
//			IBudget budget = makeNewBudget((User) user, (Account) account);
//			int id = dao.addBudget(budget);
//			budget.removeAccount((Account) account);
//			budget.setId(id);
//			budget.setTitle(UPDATE_TITLE);
//			budget.setTotalAmount(UPDATE_SUM);
//			budget.addAccount((Account) account);
//			budget.setRepeatType(RepeatType.MONTHLY);
//			budget.setStartDate(new Date());
//			dao.updateBudget(budget);
//			IBudget newBudget = dao.foundById(id);
//			Collection<Account> accounts = newBudget.getAllAccounts();
//			assertEquals(budget.getTitle(), newBudget.getTitle());
//			assertEquals(budget.getTotalAmount(), newBudget.getTotalAmount(), DELTA);
//			assertEquals(budget.getRepeatType(), newBudget.getRepeatType());
//			assertEquals(budget.getStartDate(), newBudget.getStartDate());
//			assertEquals(budget.getEndDate(), newBudget.getEndDate());
//			assertEquals(budget.getAllAccounts().size(), accounts.size());
//			accountDao.deleteAccount(account);
//			dao.removeBudget(budget);
//			userDao.deleteUser(user);
//		} catch (FinanceTrackerException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void getAllBudgetsPerUser() {
		try {
			User user = new User();
			user.setFirstName(NEW_USER_FIRST_NAME);
			user.setLastName(NEW_USER_LAST_NAME);
			user.setEmail(NEW_USER_EMAIL);
			user.setCurrency(Currency.BGN);
			user.setIsAdmin(false);
			user.setPassword(NEW_USER_PASSWORD);
			int idUser = userDao.createUser(user);
			IAccount account = new Account(NEW_ACCOUNT_NAME, NEW_ACCOUNT_SUM, user);
			accountDao.createAccount(account);
			IBudget budget = makeNewBudget((User) user, (Account) account);
			dao.addBudget(budget);
			assertNotNull(dao.getAllBudgetsByUser(userDao.getUser(idUser)));
			dao.removeBudget(budget);
			accountDao.deleteAccount(account);
			userDao.deleteUser(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllBudgets() {
		try {
			IUser user = makeNewUser();
			userDao.createUser(user);
			IAccount account = makeNewAccount((User) user);
			accountDao.createAccount(account);
			IBudget budget = makeNewBudget((User) user, (Account) account);
			dao.addBudget(budget);
			assertNotNull(dao.getAllBudgets());
			dao.removeBudget(budget);
			accountDao.deleteAccount(account);
			userDao.deleteUser(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllBudgetsByUser() {
		Collection<IBudget> budgets = new UserDAO().getAllBudgetsByUser(3051);
		for (IBudget b : budgets) {
			System.out.println(b.getTitle() + " " + b.getTotalAmount());
		}
	}

	private IBudget makeNewBudget(User user, Account account) throws FinanceTrackerException {
		IBudget budget = new Budget();
		budget.setTitle(BUDGET_TITLE);
		budget.setStartDate(new Date());
		budget.setRepeatType(RepeatType.NO_REPEAT);
		budget.setTotalAmount(BUDGET_SUM);
		budget.setUser((User) user);
		budget.addAccount(account);
		return budget;
	}

	private IUser makeNewUser() {
		IUser user = new User();
		user.setFirstName(USER_NAME);
		user.setLastName(USER_LASTNAME);
		user.setEmail(USER_EMAIL);
		user.setCurrency(Currency.BGN);
		user.setIsAdmin(false);
		try {
			user.setPassword(USER_PASSWORD);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		return user;
	}

	private IAccount makeNewAccount(User user) throws FinanceTrackerException {
		IAccount account = new Account();
		account.setSum(ACCOUNT_SUM);
		account.setOwner(user);
		account.setTitle(ACCOUNT_TITLE);
		return account;
	}

}
