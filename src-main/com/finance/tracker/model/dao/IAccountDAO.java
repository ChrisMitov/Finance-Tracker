package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IUser;

public interface IAccountDAO {

	int createAccount(IAccount account);

	void deleteAccount(IAccount account);

	IAccount getAccount(int id);

	Collection<IAccount> getAllAccountsByUser(IUser user);

	void updateAccount(IAccount account);

}