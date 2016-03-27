package com.finance.tracker.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.model.dao.IBudgetDao;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

public class BudgetTest {
	IBudgetDao dao = new BudgetDao();

	@Test
	public void addUnit() {
		IBudget budget = new Budget();
		IUserDAO userDao = new UserDAO();
		try {
			budget.setTitle("Spestoven");
			budget.setStartDate(new Date());
			budget.setRepeatType(RepeatType.MONTHLY);
			budget.setTotalAmount(2000);
			budget.setUser(userDao.getUser(10));
			int id = dao.addBudget(budget);
			assertNotNull(dao.foundById(id));
			dao.removeBudget(id);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllBudgetsPerUser() {
		IUserDAO userDao = new UserDAO();
//		User user = new User();
//		user.setFirstName("Gosho");
//		user.setLastName("Petkov");
//		user.setEmail("gosho.petkov@gmail.com");
//		user.setCurrency(Currency.BGN);
//		user.setIsAdmin(false);
//		user.setPassword("aA@");
//		userDao.createUser(user);
		
		assertNotNull(dao.getAllBudgetsByUser(userDao.getUser(10)));
	}

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
