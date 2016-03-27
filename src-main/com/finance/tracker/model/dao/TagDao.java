package com.finance.tracker.model.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.Tag;
import com.finance.tracker.validation.Validation;

public class TagDao implements ITagDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();

	@Override
	public int addTag(Tag tag) {
		try {
			new Validation().validateNotNullObject(tag);
			manager.getTransaction().begin();
			manager.persist(tag);
			manager.getTransaction().commit();
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
		return tag.getId();
	}

	@Override
	public void updateTag(Tag tag) {
		try {
			new Validation().validateNotNullObject(tag);
			manager.getTransaction().begin();
			manager.merge(tag);
			manager.getTransaction().commit();
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}

	@Override
	public void removeTag(Tag tag) {
		try {
			new Validation().validateNotNullObject(tag);
			manager.getTransaction().begin();
			manager.remove(tag);
			manager.getTransaction().commit();
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}

	@Override
	public Tag foundTagByName(String name) {
		String txtQuery = "SELECT t FROM Tag t WHERE t.name = :name";
		TypedQuery<Tag> query = manager.createQuery(txtQuery, Tag.class).setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Tag foundById(int id) {
		return manager.find(Tag.class, id);
	}

	@Override
	public Collection<Tag> getAllTagsByCategory(ICategory category) {
		@SuppressWarnings("unchecked")
		Collection<Tag> list = manager.createQuery("Select t FROM Tag t WHERE t.category = :id ")
				.setParameter("id", category).getResultList();
		return list;
	}
}
