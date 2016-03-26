package com.finance.tracker.model.dao;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.*;

import com.finance.tracker.exception.FinanceTrackerException;
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
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();
				manager.persist(user);
				entityTransaction.commit();
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					entityTransaction.rollback();
					throw e;
				}
			}
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
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
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public User getUser(int id) {
		if (id > 0) {
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();
				User user = manager.find(User.class, id);
				entityTransaction.commit();
				return user;
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					entityTransaction.rollback();
					throw e;
				}
			}
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public Collection<IUser> getAllUsers() {
		List<IUser> listOfAllFinanceOperations= manager.createQuery("SELECT u FROM user u").getResultList();
		return listOfAllFinanceOperations;
	}

	@Override
	public void updateUser(IUser user) {
		if (user != null) {
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
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}

	}

}
