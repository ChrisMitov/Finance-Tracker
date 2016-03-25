package com.finance.tracker.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

public class UserTest {

	IUserDAO user = new UserDAO();

	@Test
	public void createUser() {
		user.createUser(
				new User(1, "Petar", "Ivanov", "pesho_i@abv.bg", "Aa123", Currency.BGN, false, LocalDate.now()));
		assertNotNull(user);
	}

	@Test
	public void findUser() {
		IUser userToFind = user.getUser(1);
		System.out.println(userToFind.getFirstName() + " " + userToFind.getLastName());
		assertNotNull(user);
	}

	@Test
	public void deleteUser() {
		user.deleteUser(1);
		assertNotNull(user);
	}

	@Test
	public void updateUser() {
		user.updateUser(user);
		assertNotNull(user);
	}

}
