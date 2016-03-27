package com.finance.tracker.test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

public class UserTest {

	IUserDAO userDAO = new UserDAO();

	@Test
	public void createUser() {
		IUser user = new User("Stanio", "Hakera", "peshoto_hakercheto1@abv.bg", "AaA123", Currency.BGN, false, new Date());
		int id = userDAO.createUser(user);
		IUser newUser = userDAO.getUser(id);
		assertEquals(user.getFirstName(), newUser.getFirstName());
		userDAO.deleteUser(user);
	}
	//
	// @Test
	// public void findUser() {
	// IUser userToFind = user.getUser(2);
	// System.out.println(userToFind.getFirstName() + " " +
	// userToFind.getLastName());
	// assertNotNull(user);
	// }
	//
	// @Test
	// public void deleteUser() {
	// user.deleteUser(2);
	// assertNotNull(user);
	// }
	//
	// @Test
	// public void updateUser() {
	// User userToUpdate =user.getUser(1);
	// userToUpdate.setFirstName("Pecan");
	// user.updateUser(userToUpdate);
	// assertNotNull(user);
	// }

	// @Test
	// public void getUserByMail() {
	// IUser userWithEmail = user.getUserByMail("pesho_i@abv.bg");
	// System.out.println(userWithEmail.getFirstName() + " " +
	// userWithEmail.getLastName());
	// assertNotNull(user);
	// }

//	@Test
//	public void getAllAccounts() {
//		List<IAccount> allAccounts = user.getAllAccounts(5);
//		;
//		for (IAccount a : allAccounts) {
//			System.out.println(a.getTitle() + " " + a.getSum());
//		}
//		assertNotNull("user");
//	}

}
