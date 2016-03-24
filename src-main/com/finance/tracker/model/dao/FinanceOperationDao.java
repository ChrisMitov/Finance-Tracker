package com.finance.tracker.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.validation.Validation;

public class FinanceOperationDao {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Finance-Tracker");
	@PersistenceContext
	private EntityManager manager = emfactory.createEntityManager();
	
	public void addFinanceOperation(IFinanceOperation operation){
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
	}
	
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
	
	public IFinanceOperation foundFinanceOperationByName(String name) {
		String txtQuery = "SELECT f FROM finance_operation f WHERE f.name = :name";
		TypedQuery<IFinanceOperation> query = manager.createQuery(txtQuery, IFinanceOperation.class).setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public FinanceOperation foundById(int id) {
		return manager.find(FinanceOperation.class, id);
	}
	
	public Collection<FinanceOperation> getAllFinanceOperation() {
		List<FinanceOperation> listOfAllFinanceOperations= manager.createQuery("SELECT f FROM finance_operation f").getResultList();
		return listOfAllFinanceOperations;
	}
}
