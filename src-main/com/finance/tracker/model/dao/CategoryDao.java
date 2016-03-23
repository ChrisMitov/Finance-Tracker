package com.finance.tracker.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.validation.Validation;

public class CategoryDao {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Finance-Tracker");
	@PersistenceContext
	private EntityManager manager = emfactory.createEntityManager();

	public void addCategory(ICategory category) throws FinanceTrackerException {
		new Validation().validateNotNullObject(category);
		manager.getTransaction().begin();
		manager.persist(category);
		manager.getTransaction().commit();
	}

	public ICategory foundCategoryByName(String name) {
		TypedQuery<ICategory> query = manager.createNamedQuery("foundCategoryByName", ICategory.class)
				.setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public ICategory foundById(int id) {
		return manager.find(ICategory.class, id);
	}

	public Collection<ICategory> getAllCategories() {
		return manager.createNamedQuery("getAllCategories", ICategory.class).getResultList();
	}

}
