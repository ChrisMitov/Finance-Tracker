package com.finance.tracker.test;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.FinanceOperationType;
import com.finance.tracker.model.IAccount;
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
		Account account = (Account) acc.getAccount(2651);
		FinanceOperation finance = new FinanceOperation();
		finance.setSum(1000);
		finance.setCategory(cat);
		finance.setDescription("Vzeh si novo tv, da zamenq stariq bokluk");
		finance.setDate(new Date());
		finance.setRepeatType(RepeatType.NO_REPEAT);
		finance.setOperationType(FinanceOperationType.EXPENCES);
		finance.setAccount((Account) acc.getAccount(2651));
		TagDao tags = new TagDao();
		Collection<Tag> tagove = tags.getAllTagsByCategory(cat);
		for (Tag tag2 : tagove) {
			finance.addTag(tag2);
		}
		foDao.addFinanceOperation(finance);
		Collection<Expense> expenses = foDao.getAllExpencesByAccount(account);
		int id = 1;
		for (FinanceOperation expense : expenses) {
			id = expense.getId();
		}
		FinanceOperation financeOperation = foDao.foundById(id) ;
		assertEquals(finance.getSum(), financeOperation.getSum());
		assertEquals(finance.getDate(), financeOperation.getDate());
		assertEquals(finance.getDescription(), financeOperation.getDescription());
		assertEquals(finance.getOperationType(), financeOperation.getOperationType());
		assertEquals(finance.getAccount(), financeOperation.getAccount());
		assertEquals(finance.getCategory(), financeOperation.getCategory());
		assertEquals(finance.getPhotoAddress(), financeOperation.getPhotoAddress());
		assertEquals(finance.getRepeatType(), financeOperation.getRepeatType());
		assertEquals(tagove.size(), financeOperation.getAllTags().size());
		foDao.removeFinanceOperation(id);
	}

	@Test
	public void getAllExpences() {
		IAccountDAO dao = new AccountDAO();
		IAccount acc = dao.getAccount(2651);
		Collection<Expense> expense = foDao.getAllExpencesByAccount((Account) acc);
		for (FinanceOperation expenses2 : expense) {
			System.out.println(expenses2.getId());

			System.out.println(expenses2.getSum());
			System.out.println(expenses2.getDescription());
			System.out.println(expenses2.getCategory().getName());
			System.out.println(expenses2.getAccount().getTitle());
			System.out.println(expenses2.getDate());
		}
		assertNotNull(expense);
	}

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
