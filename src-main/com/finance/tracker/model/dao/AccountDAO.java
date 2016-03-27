package com.finance.tracker.model.dao;

import java.util.Collection;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;

public class AccountDAO implements IAccountDAO {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();


	@Override
	public int createAccount(IAccount account) {
		if (account != null) {
			try {
				manager.getTransaction().begin();
				manager.persist(account);
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
		return account.getId();
	}

	@Override
	public void deleteAccount(int index) {
		if (index > 0) {
			try {
				manager.getTransaction().begin();
				Account account = manager.find(Account.class, index);
				manager.remove(account);
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
				e.printStackTrace();
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
	public Collection<IAccount> getAllAccounts() {
		List<IAccount> listOfAllFinanceOperations= manager.createQuery("SELECT a FROM account a").getResultList();
		return listOfAllFinanceOperations;
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
