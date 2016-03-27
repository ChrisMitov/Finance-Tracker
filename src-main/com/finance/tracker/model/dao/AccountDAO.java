package com.finance.tracker.model.dao;

import java.util.Collection;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.Tag;
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
	public IAccount getAccount(int id) {
		if (id > 0) {
			try {
				manager.getTransaction().begin();
				Account account = manager.find(Account.class, id);
				manager.getTransaction().commit();
				return account;
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					manager.getTransaction().rollback();
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
	public Collection<IAccount> getAllAccountsByUser(IUser user) {
		@SuppressWarnings("unchecked")
		Collection<IAccount> listOfAllAccountByUser = manager.createQuery("SELECT a FROM Account a WHERE a.owner = :user").setParameter("user",user).getResultList();
		return listOfAllAccountByUser;
	}

	
	@Override
	public void updateAccount(IAccount account) {
		if (account != null) {
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
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}

	}

}
