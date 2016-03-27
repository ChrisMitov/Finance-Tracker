package com.finance.tracker.model.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.validation.Validation;

public class CategoryDao implements ICategoryDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();

	@Override
	public int addCategory(ICategory category) {
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
		return category.getId();
	}

	@Override
	public void removeCategory(int id) {
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
	public ICategory foundCategoryByName(String name) {
		String txtQuery = "SELECT c FROM Category c WHERE c.name = :name";
		TypedQuery<ICategory> query = manager.createQuery(txtQuery, ICategory.class).setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Category foundById(int id) {
		return manager.find(Category.class, id);
	}

	@Override
	public Collection<Category> getAllCategories() {
		@SuppressWarnings("unchecked")
		Collection<Category> listOfAllCategories = manager.createQuery("SELECT c FROM Category c").getResultList();
		return listOfAllCategories;
	}

}
