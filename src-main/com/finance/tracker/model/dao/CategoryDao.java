package com.finance.tracker.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.validation.Validation;

public class CategoryDao {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Finance-Tracker");
	@PersistenceContext
	private EntityManager manager = emfactory.createEntityManager();

	public void addCategory(ICategory category) {
		try {
			new Validation().validateNotNullObject(category);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.persist(category);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}

	public ICategory foundCategoryByName(String name) {
		String txtQuery = "SELECT c FROM Category c WHERE c.name = :name";
		TypedQuery<ICategory> query = manager.createQuery(txtQuery, ICategory.class).setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

//	public void addTagToCategory(Tag tag, ICategory category) {
//		try {
//			new Validation().validateNotNullObject(tag);
//			manager.getTransaction().begin();
//			category.addTag(tag);
//			manager.persist(category);
//			manager.getTransaction().commit();
//		} catch (FinanceTrackerException e) {
//			e.printStackTrace();
//		}
//	}

	public Category foundById(int id) {
		return manager.find(Category.class, id);
	}

	public Collection<ICategory> getAllCategories() {
		return manager.createNamedQuery("getAllCategories", ICategory.class).getResultList();
	}

}
