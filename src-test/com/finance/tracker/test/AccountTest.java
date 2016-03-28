package com.finance.tracker.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.IAccountDAO;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

public class AccountTest {

	IAccountDAO account = new AccountDAO();
	IUserDAO userDao = new UserDAO();

	@Test
	public void createAccount() throws FinanceTrackerException {
		IUser user = new User("Sasa", "sasko", "19w@abv.bg", "iiI12l", Currency.BGN, false, new Date());
		userDao.createUser(user);
		IAccount acc = new Account("Free Time", 200, (User) user);
		int idAcc = account.createAccount(acc);
		IAccount newAcc = account.getAccount(idAcc);
		assertEquals(acc.getTitle(), newAcc.getTitle());
		account.deleteAccount(acc);
		userDao.deleteUser(user.getUserId());
	}

	@Test
	public void getAllAcountsByOneUser() {
		Collection<IAccount> list = account.getAllAccountsByUser(userDao.getUser(1001));
		for (IAccount acc : list) {
			System.out.println(acc.getTitle());
			System.out.println(acc.getSum());
		}
	}

		
	 @Test
	 public void updateAccount() {
	 account.updateAccount(account.getAccount(1));
	 assertNotNull(account);
	 }

}
