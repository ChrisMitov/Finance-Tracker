package com.finance.tracker.model.dao;

import java.util.Collection;

import java.util.List;
import javax.persistence.*;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.validation.Validation;

public class UserDAO implements IUserDAO {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();

	@Override
	public int createUser(IUser user) {
		try {
			new Validation().validateNotNullObject(user);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
				throw e;
			}
		}

		return user.getUserId();
	}

	@Override
	public void deleteUser(IUser user) {
		try {
			new Validation().validateNotNullObject(user);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.remove(user);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
				throw e;
			}
		}
	}

	@Override
	public User getUser(int id) {
		try {
			new Validation().validateNegativeNumber(id);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			User user = manager.find(User.class, id);
			manager.getTransaction().commit();
			return user;
		} catch (RuntimeException e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
				throw e;
			}
		}
		return null;
	}

	@Override
	public Collection<IUser> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<IUser> listOfAllFinanceOperations = manager.createQuery("SELECT u FROM User u").getResultList();
		return listOfAllFinanceOperations;
	}

	@Override
	public void updateUser(IUser user) {
		try {
			new Validation().validateNotNullObject(user);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.merge(user);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
				throw e;
			}
		}
	}

	public IUser getUserByMail(String email) {
		try {
			new Validation().validateString(email);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		String txtQuery = "SELECT u FROM User u WHERE u.email =:email";
		TypedQuery<IUser> query = manager.createQuery(txtQuery, IUser.class).setParameter("email", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<IAccount> getAllAccounts(int id) {
		try {
			new Validation().validateNegativeNumber(id);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		Query query = manager.createQuery("Select a " + "from Account a " + "where a.user_id:=id", IAccount.class);
		try {
			return (Collection<IAccount>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}

	}

}
