package com.finance.tracker.model.dao;

import com.finance.tracker.model.IUser;

public class LogInDAO {
	
	public boolean validate(String username, String pass) {
		if(new UserDAO().isUserExisting(username)){
			return false;
		}
		IUser userToCheck = new UserDAO().getUserByMail(username);
		String password = userToCheck.getPassword();
		if (password.equals(pass)) {
			return true;
		} else {
			return false;
		}
	}
}
