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

	IUserDAO user = new UserDAO();

	// @Test
	// public void createUser() {
	// user.createUser(
	// new User(11, "Stanio", "Hakera", "@pesho_haker3b@abv.bg", "Aa123",
	// Currency.USD, true, new Date()));
	// assertNotNull(user);
	//// new User(1, "Petar", "Ivanov", "pesho_iv@abv.bg", "Aa123",
	// Currency.EUR, false, LocalDate.now()));
	//// assertNotNull(user);
	// }
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

//	@Test
//	public void getUserByMail() {
//		IUser userWithEmail = user.getUserByMail("pesho_i@abv.bg");
//		System.out.println(userWithEmail.getFirstName() + " " + userWithEmail.getLastName());
//		assertNotNull(user);
//	}
	
	@Test
	public void getAllAccounts(){
		List<IAccount> allAccounts = user.getAllAccounts(5);;
		for(IAccount a:allAccounts){
			System.out.println(a.getTitle()+" "+a.getSum());
		}
		assertNotNull("user");
	}

}
