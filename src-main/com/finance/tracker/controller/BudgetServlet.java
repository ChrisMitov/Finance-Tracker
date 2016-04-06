package com.finance.tracker.controller;

import java.io.IOException;

import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.BudgetDao;

@WebServlet("/budget")
public class BudgetServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<Budget> budgets = new BudgetDao().getAllBudgetsByUser(user);
		request.setAttribute("budgets", budgets);
		if(user.isBudgetEmpty()){
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/BlanckBudget.jsp");
			dispatcher.forward(request, response);
		}
		else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Budget.jsp");
			dispatcher.forward(request, response);
		}
	}
}