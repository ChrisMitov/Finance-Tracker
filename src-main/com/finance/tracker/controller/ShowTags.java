package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.TagDao;

/**
 * Servlet implementation class ShowTags
 */
@WebServlet("/tags")
public class ShowTags extends HttpServlet {
	private static final String CATEGORY_ID = "categoryId";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if is authorized...
		HttpSession session = request.getSession();
		int categoryId = (int) session.getAttribute(CATEGORY_ID);
		new TagDao().getAllTagsByCategory(new CategoryDao().foundById(categoryId));
		Collection<Tag> tags = new TagDao().getAllTagsByCategory(new CategoryDao().foundById(categoryId));
		request.setAttribute("category_name", new CategoryDao().foundById(categoryId).getName());
		request.setAttribute("tags", tags);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/tags.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if is authorized...
		int categoryId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		session.setAttribute(CATEGORY_ID, categoryId);
		Collection<Tag> tags = new TagDao().getAllTagsByCategory(new CategoryDao().foundById(categoryId));
		request.setAttribute("category_name", new CategoryDao().foundById(categoryId).getName());
		request.setAttribute("tags", tags);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/tags.jsp");
		dispatcher.forward(request, response);
	}

}
