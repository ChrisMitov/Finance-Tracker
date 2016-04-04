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
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

public class UserTest {

	IUserDAO userDAO = new UserDAO();

	@Test
	public void createUser() throws PasswordException {
		IUser user = new User("Stanio", "Hakera", "peshoto_hakercheto1@abv.bg", "AaA123", Currency.BGN, false,
				new Date());
		// int id = userDAO.createUser(user);
		// IUser newUser = userDAO.getUser(id);
		// assertEquals(user.getFirstName(), newUser.getFirstName());
		// userDAO.deleteUser(user);
	}

	@Test
	public void findUser() {
		IUser userToFind = userDAO.getUser(151);
		System.out.println(userToFind.getFirstName() + " " + userToFind.getLastName());
		assertNotNull(userDAO);
	}

	@Test
	public void deleteUser() {
		userDAO.deleteUser(userDAO.getUser(202));
		assertNotNull(userDAO);
	}

	@Test
	public void updateUser() {
		User userToUpdate = userDAO.getUser(151);
		userToUpdate.setFirstName("Pecan");
		userDAO.updateUser(userToUpdate);
		assertNotNull(userDAO);
	}

	@Test
	public void getUserByMail() {
		IUser userWithEmail = userDAO.getUserByMail("haho@abv.bg");
		System.out.println(userWithEmail.getFirstName() + " " + userWithEmail.getLastName());
		assertNotNull(userDAO);
	}

	@Test
	public void getAllUsers() {
		Collection<IUser> allUsers = userDAO.getAllUsers();
		for (IUser u : allUsers) {
			System.out.println(u.getFirstName() + " " + u.getLastName());
		}
		assertNotNull("userDAO");
	}

	@Test
	public void userFullSolution() throws PasswordException {
		IUser user = new User("Haralampii", "Stoyanov", "haho2@abv.bg", "Azsumvelik1", Currency.BGN, false, new Date());
		userDAO.createUser(user);
		IBudget budju = new Budget();
		try {
			budju.setTitle("Vacations");
			budju.setId(5893);
			budju.setUser((User) user);
			budju.setRepeatType(RepeatType.MONTHLY);
			budju.setStartDate(new Date());
			budju.setEndDate(new Date());

		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		new BudgetDao().addBudget(budju);
		IAccount acc = new Account();
		try {
			acc.setTitle("Books");
			acc.setOwner((User) user);
			acc.setId(7893);
			acc.setSum(8576);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void checkForExistingUser() throws PasswordException {
		IUser user = new User("Malina", "Petkova", "malinka@abv.bg", "Aa1Aa1Aa1A", Currency.EUR, false, new Date());
		if (userDAO.isUserExisting(user.getEmail())) {
			System.out.println("YES!");
		} else {
			System.out.println("NO!");
		}

	}
}
