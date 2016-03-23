package com.finance.tracker.model.dao;

import java.util.List;

import com.finance.tracker.model.IUser;

public interface IUserDAO {

	void createUser(IUser user);

	void deleteUser(int index);

	IUser getUsre(int id);

	List<IUser> getAllUsers();

	void updateUser(IUser user);

}