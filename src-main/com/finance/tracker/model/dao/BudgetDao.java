package com.finance.tracker.model.dao;

import java.util.Collection;

import java.util.List;

import javax.persistence.EntityManager;
<<<<<<< HEAD
import javax.persistence.NoResultException;
=======
>>>>>>> 9e3353e279baef657999ff56707dc364a6c6f350
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.validation.Validation;

public class BudgetDao implements IBudgetDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();
<<<<<<< HEAD
	
	@Override
	public int addBudget(IBudget budget){
=======

	@Override
	public int addBudget(IBudget budget) {
>>>>>>> 9e3353e279baef657999ff56707dc364a6c6f350
		try {
			new Validation().validateNotNullObject(budget);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.persist(budget);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
<<<<<<< HEAD
		return budget.getBudgetId();
=======
		return foundBudgetByTitle(budget.getTitle()).getId();
>>>>>>> 9e3353e279baef657999ff56707dc364a6c6f350
	}

	@Override
	public void removeBudget(int id) {
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
	public IBudget foundBudgetByTitle(String name) {
		String txtQuery = "SELECT b FROM Budget b WHERE b.title = :title";
		Query query = manager.createQuery(txtQuery, Budget.class).setParameter("title", name);
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<Budget> budgets = query.getResultList();
		if (budgets != null || budgets.size() > 0) {
			return budgets.get(0);
		}
		return null;
	}

	@Override
	public Budget foundById(int id) {
		return manager.find(Budget.class, id);
	}

	public Collection<Budget> getAllBudgetsByUser(IUser user) {
		Query query = manager.createQuery("from Budget b where b.user = :user").setParameter("user", user);
		@SuppressWarnings("unchecked")
		Collection<Budget> budgets = query.getResultList();
		return budgets;
	}

	@Override
	public Collection<Budget> getAllBudgets() {
		@SuppressWarnings("unchecked")
		List<Budget> listOfAllBudgets = manager.createQuery("SELECT b FROM Budget b").getResultList();
		return listOfAllBudgets;
	}
}
