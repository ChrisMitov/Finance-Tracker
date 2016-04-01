package com.finance.tracker.model.dao;

import java.util.Collection;
import java.util.List;

import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;

public interface IUserDAO {

	int createUser(IUser user);

	void deleteUser(int id);

	User getUser(int id);

	Collection<IUser> getAllUsers();

	void updateUser(IUser user);
	
	IUser getUserByMail(String email);
	
	Collection<IAccount> getAllAccounts(int id);
	
	boolean isUserExsisting(String email);

}