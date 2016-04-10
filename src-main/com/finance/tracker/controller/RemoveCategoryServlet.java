package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.Category;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.TagDao;

@WebServlet("/categoryRemove")
public class RemoveCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = new CategoryDao().foundById(categoryId);
		Collection<IFinanceOperation> operations = new FinanceOperationDao()
				.getAllFInanceOperationsByCategory(category);
		if (!operations.isEmpty()) {
			request.setAttribute("operationsCategory", "First delete all finance operations");
			ServletContext servletContext = getServletContext();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/category");
			requestDispatcher.forward(request, response);
			return;
		}
		Collection<Tag> tags = new TagDao().getAllTagsByCategory(category);
		for (Tag tag : tags) {
			new TagDao().removeTag(tag.getId());
		}
		new CategoryDao().removeCategory(categoryId);
		response.sendRedirect("./category");
	}

}
