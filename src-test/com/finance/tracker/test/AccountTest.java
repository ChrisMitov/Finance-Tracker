package com.finance.tracker.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.IAccountDAO;

public class AccountTest {

	IAccountDAO account = new AccountDAO();

	@Test
	public void createAccount() throws FinanceTrackerException {
		account.createAccount(new Account("Fun", 500));
		assertNotNull(account);
	}

	@Test
	public void findUser() {
		IAccount accountToFind = account.getAccount(1);
		assertNotNull(accountToFind);
	}

	@Test
	public void deleteAccount() {
		account.deleteAccount(1);
		assertNotNull(account);
	}

	@Test
	public void updateAccount() {
		account.updateAccount(account.getAccount(1));
		assertNotNull(account);
	}

}
