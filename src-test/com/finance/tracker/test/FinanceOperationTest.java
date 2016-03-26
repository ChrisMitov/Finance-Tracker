package com.finance.tracker.test;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.FinanceOperationType;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.TagDao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class FinanceOperationTest {
	private FinanceOperationDao foDao = new FinanceOperationDao();

	@Test
	public void addFinanceOperation() throws FinanceTrackerException {
		CategoryDao dao = new CategoryDao();
		Category cat = (Category) dao.foundCategoryByName("TV");
		IFinanceOperation finance = new FinanceOperation();
		finance.setSum(500);
		finance.setCategory(cat);
		finance.setDescription("Kupih si mnogo qka kniga");
		finance.setDate(new Date());
		finance.setRepeatType(RepeatType.MONTHLY);
		finance.setType("Expenses");
		finance.setOperationType(FinanceOperationType.EXPENCES);
		// finance.setAccount(new Account("Spestovna",1000));
		TagDao tags = new TagDao();
		Collection<Tag> tagove = tags.getAllTagsByCategory(cat);
		for (Tag tag2 : tagove) {
			finance.addTag(tag2);
		}
		foDao.addFinanceOperation(finance);
		// Collection<FinanceOperation> operations =
		// foDao.getAllFinanceOperation();
		// int id = 1;
		// for (FinanceOperation financeOperation : operations) {
		// id = financeOperation.getId();
		// }
		// System.out.println(id);
		assertNotNull(foDao);
	}

	@Test
	public void findFinanceOperation() {
		assertNotNull(foDao.foundById(1001));
	}
	
	@Test 
	public void removeFinanceOperation(){
		foDao.removeFinanceOperation(1251);
	}
}
