package com.finance.tracker.test;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.FinanceOperationType;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Income;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.IAccountDAO;
import com.finance.tracker.model.dao.IFinanceOperationDao;
import com.finance.tracker.model.dao.TagDao;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

public class FinanceOperationTest {
	private IFinanceOperationDao foDao = new FinanceOperationDao();

	@Test
	public void addFinanceOperation() throws FinanceTrackerException {
		CategoryDao dao = new CategoryDao();
		IAccountDAO acc = new AccountDAO();
		Category cat = (Category) dao.foundCategoryByName("TV");
		IFinanceOperation finance = new FinanceOperation();
		finance.setSum(200);
		finance.setCategory(cat);
		finance.setDescription("Kupih si mnogo qk Samsung");
		finance.setDate(new Date());
		finance.setRepeatType(RepeatType.MONTHLY);
		finance.setOperationType(FinanceOperationType.INCOMES);
		finance.setAccount((Account) acc.getAccount(2651));
		TagDao tags = new TagDao();
		Collection<Tag> tagove = tags.getAllTagsByCategory(cat);
		for (Tag tag2 : tagove) {
			finance.addTag(tag2);
		}
		foDao.addFinanceOperation(finance);
		assertNotNull(foDao);
	}

	// @Test
	// public void findFinanceOperation() {
	// assertNotNull(foDao.foundById(1001));
	// }
	//
//	@Test
//	public void removeFinanceOperation() {
//		foDao.removeFinanceOperation(2101);
//		assertNull(foDao.foundById(2101));
//	}

//	@Test
//	public void getAllExpences() {
//		IAccountDAO dao = new AccountDAO();
//		IAccount acc = dao.getAccount(1901);
//		Collection<Expense> expense = foDao.getAllExpencesByAccount((Account) acc);
//		for (FinanceOperation expenses2 : expense) {
//			System.out.println(expenses2.getId());
//
//			System.out.println(expenses2.getSum());
//			System.out.println(expenses2.getDescription());
//			System.out.println(expenses2.getCategory().getName());
//			System.out.println(expenses2.getAccount().getTitle());
//			System.out.println(expenses2.getDate());
//		}
//		assertNotNull(expense);
//	}
//
	@Test
	public void getAllIncomes() {
		IAccountDAO dao = new AccountDAO();
		IAccount acc = dao.getAccount(2651);
		Collection<Income> income = foDao.getAllIncomeByAccount((Account) acc);
		for (FinanceOperation income2 : income) {
			System.out.println(income2.getId());

			System.out.println(income2.getSum());
			System.out.println(income2.getDescription());
			System.out.println(income2.getCategory().getName());
			System.out.println(income2.getAccount().getTitle());
			System.out.println(income2.getDate());
		}
		assertNotNull(income);
	}
}
