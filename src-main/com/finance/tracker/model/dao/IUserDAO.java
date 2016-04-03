package com.finance.tracker.model.dao;

import java.util.Collection;
import java.util.Date;

import com.finance.tracker.model.Currency;
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
	
	String getFirstNameById(int id);

	String getLastNameById(int id);
	
	String getPasswordById(int id);
	
	String getEmailById(int id);
	
	Date getDateByID(int id);
	
	Currency getCurrencyById(int id);

}