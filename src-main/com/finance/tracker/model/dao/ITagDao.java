package com.finance.tracker.model.dao;

import java.util.Collection;

import com.finance.tracker.model.Category;
import com.finance.tracker.model.Tag;

public interface ITagDao {

	void addTag(Tag tag);

	void updateTag(int id, Tag tag);

	void removeTag(int id);

	Tag foundTagByName(String name);

	Tag foundById(int id);

	Collection<Tag> getAllTagsByCategory(Category category);

}