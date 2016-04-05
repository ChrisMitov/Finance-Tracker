package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.UserDAO;


@WebServlet("/addCategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if is autenticated...
		
		User user = new UserDAO().getUser(101);
		Collection<Category> categories = new CategoryDao().getAllCategoriesByUser(user); 
		request.setAttribute("categories", categories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/addCategory.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new UserDAO().getUser(101);
			String name = request.getParameter("name");
			ICategory category = new Category(name, user);
			new CategoryDao().addCategory(category);
			
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./category");
	}

}
