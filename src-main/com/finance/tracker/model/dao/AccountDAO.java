package com.finance.tracker.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IUser;
import com.finance.tracker.validation.Validation;

public class AccountDAO implements IAccountDAO {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();

	@Override
	public int createAccount(IAccount account) {
		try {
			new Validation().validateNotNullObject(account);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.persist(account);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
		return account.getId();
	}

	@Override
	public void deleteAccount(IAccount account) {
		try {
			new Validation().validateNotNullObject(account);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.remove(account);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}
	
	@Override
	public void deleteAccount(int id) {
		try {
			new Validation().validateNegativeNumber(id);;
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.remove(getAccount(id));
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}

	@Override
	public IAccount getAccount(int id) {
		try {
			new Validation().validateNegativeNumber(id);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		return manager.find(Account.class, id);
	}

	@Override
	public Collection<IAccount> getAllAccountsByUser(IUser user) {
		try {
			new Validation().validateNotNullObject(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		Collection<IAccount> listOfAllAccountByUser = manager
				.createQuery("SELECT a FROM Account a WHERE a.owner = :user").setParameter("user", user)
				.getResultList();
		return listOfAllAccountByUser;
	}

	@Override
	public void updateAccount(IAccount account) {
		try {
			new Validation().validateNotNullObject(account);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.merge(account);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
				throw e;
			}
		}
	}
}
