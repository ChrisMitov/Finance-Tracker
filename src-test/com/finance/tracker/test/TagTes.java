package com.finance.tracker.test;

import org.junit.Test;
import static org.junit.Assert.*;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.TagDao;

public class TagTes {
	@Test
	public void addTag() throws FinanceTrackerException {
		TagDao dao = new TagDao();
		CategoryDao catDao = new CategoryDao();
		dao.addTag(new Tag("Fentuzi", (Category) catDao.foundCategoryByName("Books")));
		assertNotNull(dao.foundTagByName("Fentuzi"));
	}
}
