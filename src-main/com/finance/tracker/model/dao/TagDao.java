package com.finance.tracker.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Tag;
import com.finance.tracker.validation.Validation;

public class TagDao {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Finance-Tracker");
	@PersistenceContext
	private EntityManager manager = emfactory.createEntityManager();

	public void addTag(Tag tag) {
		try {
			new Validation().validateNotNullObject(tag);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.persist(tag);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}

	public Tag foundTagByName(String name) {
		String txtQuery = "SELECT t FROM Tag t WHERE t.name = :name";
		TypedQuery<Tag> query = manager.createQuery(txtQuery, Tag.class).setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	public Tag foundById(int id) {
		return manager.find(Tag.class, id);
	}

//	public Collection<Tag> getAllTagsByCategory(ICategory category) {
//		return manager.createNamedQuery("getAllCategories", Tag.class).s;
//	}
}
