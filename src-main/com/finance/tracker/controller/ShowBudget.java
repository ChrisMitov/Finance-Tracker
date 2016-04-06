package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.dao.UserDAO;


@WebServlet("/allBudgets")
public class ShowBudget extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IUser user = ((IUser) request.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME));
		Collection<IBudget> budgets = new UserDAO().getAllBudgetsByUser(user.getUserId());
		request.setAttribute("budgets", budgets);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Budget.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
