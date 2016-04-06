package com.finance.tracker.model.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.validation.Validation;

public class BudgetDao implements IBudgetDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();

	@Override
	public int addBudget(IBudget budget) {
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
		return foundBudgetByTitle(budget.getTitle()).getId();
	}

	@Override
	public void updateBudget(IBudget budget) {
		try {
			new Validation().validateNotNullObject(budget);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.merge(budget);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}

	@Override
	public void removeBudget(IBudget budget) {
		try {
			new Validation().validateNotNullObject(budget);
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.remove(budget);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
				throw e;
			}
		}
	}

	@Override
	public void removeBudgetById(int id) {
		try {
			new Validation().validateNegativeNumber(id);
			;
		} catch (FinanceTrackerException e1) {
			e1.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.remove(foundById(id));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
				throw e;
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
		if (budgets == null || budgets.size() < 0) {
			return null;
		}
		return budgets.get(0);
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
		Collection<Budget> listOfAllBudgets = manager.createQuery("SELECT b FROM Budget b").getResultList();
		return listOfAllBudgets;
	}

	public String getTitleById(int id) {
		IBudget budget = foundById(id);
		return budget.getTitle();
	}

	public String getStartDateById(int id) {
		IBudget budget = foundById(id);
		Date date = budget.getStartDate();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String data = df.format(date);
		return data;
	}

	public String getEndDateById(int id) {
		IBudget budget = foundById(id);
		Date date = budget.getEndDate();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String data = df.format(date);
		return data;
	}

	public double getSumById(int id) {
		IBudget budget = foundById(id);
		double sum = budget.getTotalAmount();
		return sum;
	}

	public RepeatType getTypeById(int id) {
		IBudget budget = foundById(id);
		RepeatType type = budget.getRepeatType();
		return type;
	}
}
