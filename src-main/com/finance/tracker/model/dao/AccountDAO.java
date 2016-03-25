package com.finance.tracker.model.dao;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.IAccount;

public class AccountDAO implements IAccountDAO {
	@PersistenceContext
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Finance-Tracker");
	@Resource
	private EntityManager manager = emfactory.createEntityManager();
	private EntityTransaction entityTransaction = null;

	@Override
	public void createAccount(IAccount account) {
		if (account != null) {
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();
				manager.persist(account);
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
	public void deleteAccount(int index) {
		if (index > 0) {
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();

				IAccount account = manager.find(IAccount.class, index);
				manager.remove(account);
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
	public IAccount getAccount(int id) {
		if (id > 0) {
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();
				IAccount account = manager.find(IAccount.class, id);
				entityTransaction.commit();
				return account;
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
	public Collection<IAccount> getAllAccounts() {
		List<IAccount> listOfAllFinanceOperations= manager.createQuery("SELECT a FROM account a").getResultList();
		return listOfAllFinanceOperations;
	}

	@Override
	public void updateAccount(IAccount account) {
		if (account != null) {
			try {
				entityTransaction = manager.getTransaction();
				entityTransaction.begin();
				manager.merge(account);
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
