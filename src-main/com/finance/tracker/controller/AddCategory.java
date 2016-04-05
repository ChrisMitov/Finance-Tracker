package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.UserDAO;


@WebServlet("/addCategory")
public class AddCategory extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		User user = new UserDAO().getUser(101);
		Collection<Category> categories = new CategoryDao().getAllCategoriesByUser(user); 
		request.setAttribute("categories", categories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/addCategory.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
			String name = request.getParameter("name");
			ICategory category = new Category(name, user);
			new CategoryDao().addCategory(category);
			
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./category");
	}

}
