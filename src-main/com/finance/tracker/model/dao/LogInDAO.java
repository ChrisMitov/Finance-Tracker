package com.finance.tracker.model.dao;

import com.finance.tracker.model.IUser;

public class LogInDAO {
	IUserDAO user = new UserDAO();

	public boolean validate(String username, String pass) {
		IUser userToCheck = user.getUserByMail(username);
		String password = userToCheck.getPassword();
		if (password.equals(pass)) {
			return true;
		} else {
			return false;
		}
	}
}
