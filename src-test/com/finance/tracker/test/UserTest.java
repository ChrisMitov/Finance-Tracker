package com.finance.tracker.test;


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
		user.createUser(new User(1, "Ivan", "Petrov", "ivan_p@abv.bg", "IvanPetrov123", Currency.BGN));
	}

	@Test
	public void findUser(int id) {
		user.getUsre(id);
	}
	
	@Test
	public void deleteUser(IUser userToRemove){
		user.deleteUser(userToRemove);
	}

}
