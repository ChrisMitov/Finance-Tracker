package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.TagDao;
import com.finance.tracker.view.model.TagModel;
import com.google.gson.Gson;

/**
 * Servlet implementation class ShowTagsRest
 */
@WebServlet("/showTags")
public class ShowTagsRest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		int categoryId = Integer.valueOf(request.getParameter("catId"));
		System.out.println(categoryId);
		Collection<Tag> tags = new TagDao().getAllTagsByCategory(new CategoryDao().foundById(categoryId));
		Collection<TagModel> returnTags = new LinkedList<TagModel>();
		for (Tag tag : tags) {
			returnTags.add(new TagModel(tag.getName()));
		}
		response.getWriter().println(new Gson().toJson(returnTags));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
