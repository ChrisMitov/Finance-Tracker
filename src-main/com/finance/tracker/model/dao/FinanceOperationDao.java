package com.finance.tracker.model.dao;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.Tag;
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
		Date date = operation.getDate();
		date.setYear(date.getYear() - 1900);
		if (operation.getDate().after(new Date())) {
			addMany(operation);
		} else {
			addMany(operation);
				int howManyTimesToAdd = addManyTimes(operation);
				int repeatDays = getDaysToRepeat(operation.getRepeatType());
				for (int i = 0; i < howManyTimesToAdd; i++) {
					try {
						IFinanceOperation operationTmp =  new FinanceOperation();
						operationTmp.setAccount(operation.getAccount());
						operationTmp.setCategory(operation.getCategory());
						operationTmp.setDescription(operation.getDescription());
						operationTmp.setOperationType(operation.getOperationType());
						operationTmp.setPhotoAddress(operation.getPhotoAddress());
						operationTmp.setRepeatType(operation.getRepeatType());
						operationTmp.setSum(operation.getSum());
						Calendar c = Calendar.getInstance();
						c.setTime(date);
						c.add(Calendar.DATE, repeatDays);
						date = c.getTime();
						operation.setDate(date);
						operationTmp.setDate(date);
						Collection<Tag> tags = operation.getAllTags();
						for (Tag tag : tags) {
							operationTmp.addTag(tag);
						}
						addMany(operationTmp);
					} catch (FinanceTrackerException e) {
						e.printStackTrace();
					}
				}
		}
		return operation.getId();
	}
	
	private void addMany(IFinanceOperation operation){
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

	@Override
	public void removeFinanceOperation(IFinanceOperation operation) {
		try {
			new Validation().validateNotNullObject(operation);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		try {
			manager.getTransaction().begin();
			manager.remove(operation);
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

	private int addManyTimes(IFinanceOperation operation) {
		if (operation.getRepeatType().equals(RepeatType.NO_REPEAT)) {
			return 1;
		}
		Date date = operation.getDate();
//		date.setYear(date.getYear() - 1900);
		long days = daysBetween(date, new Date());
		int repeatDays = getDaysToRepeat(operation.getRepeatType());
		return (int) (days / repeatDays);

	}

	private int getDaysToRepeat(RepeatType type) {
		if (type.equals(RepeatType.DAILY))
			return 1;
		if (type.equals(RepeatType.WEEKLY))
			return 7;
		if (type.equals(RepeatType.MONTHLY))
			return 30;
		if (type.equals(RepeatType.YEARLY))
			return 365;
		return 0;
	}

	private static long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / (24 * 60 * 60 * 1000);
		return Math.abs(difference);
	}
}
