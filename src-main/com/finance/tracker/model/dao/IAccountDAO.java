package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.IAccount;

public interface IAccountDAO {

	int createAccount(IAccount account);

	void deleteAccount(int index);

	IAccount getAccount(int id);

	Collection<IAccount> getAllAccounts();

	void updateAccount(IAccount account);

}