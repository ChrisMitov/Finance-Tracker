package com.finance.tracker.model.dao;

import java.util.Collection;
import com.finance.tracker.model.IUser;

public interface IUserDAO {

	void createUser(IUser user);

	void deleteUser(int index);

	IUser getUser(int id);

	Collection<IUser> getAllUsers();

	void updateUser(IUser user);

}