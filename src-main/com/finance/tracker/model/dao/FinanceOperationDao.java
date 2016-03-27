package com.finance.tracker.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.FinanceOperationType;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Income;
import com.finance.tracker.validation.Validation;

public class FinanceOperationDao implements IFinanceOperationDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();

	@Override
	public int addFinanceOperation(IFinanceOperation operation) {
		try {
			new Validation().validateNotNullObject(operation);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.persist(operation);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
		return operation.getId();
	}

	@Override
	public void removeFinanceOperation(int id) {
		try {
			new Validation().validateNegativeNumber(id);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.remove(foundById(id));
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}

	@Override
	public FinanceOperation foundById(int id) {
		return manager.find(FinanceOperation.class, id);
	}

	@Override
	public Collection<Expense> getAllExpencesByAccount(Account account) {
		Query query = manager.createQuery(
				"From FinanceOperation f where f.operationType = :operationType and f.account = :account_id");
		query.setParameter("operationType", FinanceOperationType.EXPENCES);
		query.setParameter("account_id", account);
		@SuppressWarnings("unchecked")
		Collection<Expense> expense = query.getResultList();
		return expense;
	}

	@Override
	public Collection<Income> getAllIncomeByAccount(Account account) {
		Query query = manager.createQuery(
				"From FinanceOperation f where f.operationType = :operationType and f.account = :account_id");
		query.setParameter("operationType", FinanceOperationType.INCOMES);
		query.setParameter("account_id", account);
		@SuppressWarnings("unchecked")
		Collection<Income> income = query.getResultList();
		return income;
	}
}
