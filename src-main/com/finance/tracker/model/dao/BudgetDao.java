package com.finance.tracker.model.dao;

import java.util.Collection;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.validation.Validation;

public class BudgetDao implements IBudgetDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();
	
	@Override
	public int addBudget(IBudget budget){
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
		return budget.getBudgetId();
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
	public IBudget foundBudgetByName(String name) {
		String txtQuery = "SELECT b FROM Budget b WHERE b.name = :name";
		TypedQuery<IBudget> query = manager.createQuery(txtQuery, IBudget.class).setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	@Override
	public Budget foundById(int id) {
		return manager.find(Budget.class, id);
	}
	
	@Override
	public Collection<Budget> getAllBudgets() {
		List<Budget> listOfAllBudgets = manager.createQuery("SELECT b FROM Budget b").getResultList();
		return listOfAllBudgets;
	}
}
