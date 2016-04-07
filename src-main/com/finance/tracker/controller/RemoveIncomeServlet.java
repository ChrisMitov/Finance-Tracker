package com.finance.tracker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.model.dao.FinanceOperationDao;

@WebServlet("/removeIncome")
public class RemoveIncomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		response.sendRedirect("./incomes");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int incomeId = Integer.parseInt(request.getParameter("id"));
		new FinanceOperationDao().removeFinanceOperation(incomeId);
		response.sendRedirect("./incomes");
	}

}
