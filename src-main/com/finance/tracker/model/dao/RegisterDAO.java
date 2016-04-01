package com.finance.tracker.model.dao;

import com.finance.tracker.model.IUser;

public class RegisterDAO {
	IUserDAO userDAO = new UserDAO();
	
	
	public void registerNewuser(IUser user){
		userDAO.createUser(user);
	}
	
	public void passwordValidation(){
		
	}
	
	public void doesUserExist(IUser user){
		
	}

}
