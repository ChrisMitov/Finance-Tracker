package com.finance.tracker.test;

import static org.junit.Assert.*;
import java.time.LocalDate;

import org.junit.Test;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

public class UserTest {

	IUserDAO user = new UserDAO();

	@Test
	public void createUser() {
		user.createUser(
				new User(3, "Pesho", "Hakera", "pesho_hakera@abv.bg", "Aa123", Currency.USD, true, LocalDate.now()));
		assertNotNull(user);
//				new User(1, "Petar", "Ivanov", "pesho_iv@abv.bg", "Aa123", Currency.EUR, false, LocalDate.now()));
//		assertNotNull(user);
	}

	@Test
	public void findUser() {
		IUser userToFind = user.getUser(2);
		System.out.println(userToFind.getFirstName() + " " + userToFind.getLastName());
		assertNotNull(user);
	}

	@Test
	public void deleteUser() {
		user.deleteUser(2);
		assertNotNull(user);
	}

	@Test
	public void updateUser() {
		user.updateUser(user.getUser(1));
		assertNotNull(user);
	}

}
