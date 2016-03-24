package com.finance.tracker.model.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.*;

import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;

public class UserDAO implements IUserDAO {
	@PersistenceContext
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Finance-Tracker");
	@Resource
	private EntityManager manager = emfactory.createEntityManager();
	private EntityTransaction entityTransaction = null;

	
	@Override
	public void createUser(IUser user) {
		if (user != null) {
			IUser newUser = new User();
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setCurrency(user.getCurrency().getId());
			newUser.setIsAdmin(user.isAdmin());
			newUser.setStartDate(user.getJointedDate());
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();
				manager.persist(newUser);
				entityTransaction.commit();
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					entityTransaction.rollback();
					throw e;
				}
			}
		}
	}

	@Override
	public void deleteUser(int index) {
		if (index > 0) {
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();

				IUser user = manager.find(IUser.class, index);
				manager.remove(user);
				entityTransaction.commit();
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					entityTransaction.rollback();
					throw e;
				}
			}
		}

	}

	@Override
	public IUser getUsre(int id) {
		if (id > 0) {
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();

				IUser user = manager.find(IUser.class, id);
				entityTransaction.commit();
				return user;
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					entityTransaction.rollback();
					throw e;
				}
			}
		}
		return null;
	}

	@Override
	public List<IUser> getAllUsers() {
		return null;
	}

	@Override
	public void updateUser(IUser user) {
		if (user != null) {
			IUser newUser = new User();
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setCurrency(user.getCurrency().getId());
			newUser.setIsAdmin(user.isAdmin());
			newUser.setStartDate(user.getJointedDate());
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();
				manager.merge(user);
				entityTransaction.commit();
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					entityTransaction.rollback();
					throw e;
				}
			}
		}

	}

}
