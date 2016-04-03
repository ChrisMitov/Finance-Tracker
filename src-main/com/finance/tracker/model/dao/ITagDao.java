package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.Tag;

public interface ITagDao {

	int addTag(Tag tag);

	void updateTag(Tag tag);

	void removeTag(Tag tag);
	
	void removeTag(int id);

	Tag foundTagByName(String name);

	Tag foundById(int id);

	Collection<Tag> getAllTagsByCategory(ICategory category);

}