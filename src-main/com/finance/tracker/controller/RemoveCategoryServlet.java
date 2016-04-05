package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.TagDao;

@WebServlet("/category/remove")
public class RemoveCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		response.sendRedirect("../category");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Collection<Tag> tags = new TagDao().getAllTagsByCategory(new CategoryDao().foundById(categoryId));
		for (Tag tag : tags) {
			new TagDao().removeTag(tag.getId());
		}
		new CategoryDao().removeCategory(categoryId);
		response.sendRedirect("../category");
	}

}
