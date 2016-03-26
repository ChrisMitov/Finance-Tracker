package com.finance.tracker.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collection;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.ICategoryDao;
import com.finance.tracker.model.dao.ITagDao;
import com.finance.tracker.model.dao.TagDao;

public class TagTest {
	ITagDao dao = new TagDao();
	ICategoryDao catDao = new CategoryDao();
	@Test
	public void addTag() throws FinanceTrackerException {
		Category cat =  (Category) catDao.foundCategoryByName("TV");
		dao.addTag(new Tag("Pony", cat));
		dao.addTag(new Tag("KT", cat));
		assertNotNull(dao.foundTagByName("KT"));
	}
	
//	@Test
//	public void removeTag(){
//		Collection<Category> categories = catDao.getAllCategories();
//		int id = 1;
//		for (Category category : categories) {
//			id = category.getId();
//		}
//		Collection<Tag> tags = dao.getAllTagsByCategory(catDao.foundById(id));
//		for (Tag tag : tags) {
//			id = tag.getId();
//			dao.removeTag(id);
//		}
//		assertNull(dao.foundById(id));
//	}
}
