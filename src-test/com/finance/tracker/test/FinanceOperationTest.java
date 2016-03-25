package com.finance.tracker.test;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.TagDao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

public class FinanceOperationTest {
	private FinanceOperationDao foDao = new FinanceOperationDao();

	@Test
	public void addFinanceOperation() throws FinanceTrackerException {
		CategoryDao dao = new CategoryDao();
		Category cat = (Category) dao.foundCategoryByName("Clothes");
		IFinanceOperation finance = new FinanceOperation();
		finance.setSum(100);
		finance.setCategory(cat);
		finance.setDescription("Kupih si mnogo rqdka kniga");
		finance.setDate(LocalDate.now());
		finance.setRepeatType(RepeatType.MONTHLY);
		finance.setType("Expenses");
		finance.setAccount(new Account("Spestovna",1000));
		TagDao tags = new TagDao();
		Collection<Tag> tagove = tags.getAllTagsByCategory(cat);
		for (Tag tag2 : tagove) {
			finance.addTag(tag2);
		}
		foDao.addFinanceOperation(finance);
		Collection<FinanceOperation> operations = foDao.getAllFinanceOperation();
		int id = 1;
		for (FinanceOperation financeOperation : operations) {
			id = financeOperation.getId();
		}
		assertNotNull(foDao.foundById(id));
	}
}
