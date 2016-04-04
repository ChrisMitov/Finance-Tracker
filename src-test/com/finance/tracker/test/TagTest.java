package com.finance.tracker.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.exception.PasswordException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.ICategoryDao;
import com.finance.tracker.model.dao.ITagDao;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.TagDao;
import com.finance.tracker.model.dao.UserDAO;

public class TagTest {
	private static final int NUMBER_OF_ADDED_TAGS = 5;
	private static final String SECOND_TAG_TEST_NAME = "Airbus";
	private static final String TAG_TEST_NAME = "Boing 737";
	private static final String CATEGORY_TEST_NAME = "Airplane";
	ITagDao dao = new TagDao();
	ICategoryDao catDao = new CategoryDao();
	IUserDAO userDao = new UserDAO();
	@Test
	public void addTag()  {
		try {
			IUser user = createUser();
			userDao.createUser(user);
			ICategory cat = makeCategory((User) user);
			int id = catDao.addCategory(cat);
			Tag tag = makeTag(TAG_TEST_NAME, id);
			int tagId = dao.addTag(tag);
			Tag newTag = dao.foundById(tagId);
			assertEquals(tag.getName(), newTag.getName());
			assertEquals(tag.getCategory(), newTag.getCategory());
			dao.removeTag(tag);
			catDao.removeCategory(cat);
			userDao.deleteUser(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		} catch (PasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTag(){
		try {
			IUser user = createUser();
			userDao.createUser(user);
			ICategory cat = makeCategory((User) user);
			int id = catDao.addCategory(cat);
			Tag tag = makeTag(TAG_TEST_NAME, id);
			int tagId = dao.addTag(tag);
			tag.setName(SECOND_TAG_TEST_NAME);
			tag.setCategory(catDao.foundById(id));
			dao.updateTag(tag);
			Tag newTag = dao.foundById(tagId);
			assertEquals(newTag.getName(), tag.getName());
			assertEquals(newTag.getCategory(), tag.getCategory());
			dao.removeTag(newTag);
			catDao.removeCategory(cat);
			userDao.deleteUser(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		} catch (PasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getAllTagsByCategory(){
		try {
			IUser user = createUser();
			userDao.createUser(user);
			ICategory cat = makeCategory((User) user);
			int id = catDao.addCategory(cat);
			List<Tag> tags = new ArrayList<Tag>();
			for (int i = 0; i < NUMBER_OF_ADDED_TAGS; i++) {
				Tag tag = makeTag(TAG_TEST_NAME + i, id);
				tags.add(tag);
				dao.addTag(tag);
			}
			Collection<Tag> getTags = dao.getAllTagsByCategory(cat);
			assertEquals(tags.size(), getTags.size());
			for (Tag tag : getTags) {
				dao.removeTag(tag);
			}
			catDao.removeCategory(cat);
			userDao.deleteUser(user);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		} catch (PasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ICategory makeCategory(User user) throws FinanceTrackerException {
		ICategory cat = new Category(CATEGORY_TEST_NAME,user);
		return cat;
	}

	private Tag makeTag(String name ,int id) throws FinanceTrackerException {
		Tag tag = new Tag();
		tag.setName(name);
		tag.setCategory(catDao.foundById(id));
		return tag;
	}
	
	private IUser createUser() throws PasswordException{
		IUser user = new User("Chiko", "Alvarez", "chiko@gmail.com", "ahajA22", Currency.BGN, false, new Date());
		return user;
	}
}
