package com.finance.tracker.model.dao;

import java.util.Collection;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;

public interface IUserDAO {

	int createUser(IUser user);

	void deleteUser(IUser user);

	User getUser(int id);

	Collection<IUser> getAllUsers();

	void updateUser(IUser user);
	
	IUser getUserByMail(String email);
	
	Collection<IAccount> getAllAccounts(int id);
	
	boolean isUserExisting(String email);
	
	int getUserId(String email);

}