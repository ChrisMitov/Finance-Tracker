package com.finance.tracker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finance.tracker.model.dao.BudgetDao;

@WebServlet("/removeBudget")
public class RemoveBudget extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		response.sendRedirect("./budget");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int budgetId = Integer.parseInt(request.getParameter("id"));
		new BudgetDao().removeBudgetById(budgetId);
		response.sendRedirect("./budget");
	}

}
