package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.FinanceOperationDao;

@WebServlet("/incomes")
public class IncomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
		Collection<IFinanceOperation> incomes = new ArrayList<>();
		for (IAccount account : accounts) {
			incomes.addAll(new FinanceOperationDao().getAllIncomeByAccount((Account) account));

		}
		if (incomes.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/blankIncome.jsp");
			dispatcher.forward(request, response);
			return;
		}
		Collections.sort((List<IFinanceOperation>) incomes, (e1, e2) -> e1.getDate().getDay() - e2.getDate().getDay());
		request.setAttribute("accounts", accounts);
		request.setAttribute("currency", user.getCurrency());
		request.setAttribute("incomes", incomes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/incomes.jsp");
		dispatcher.forward(request, response);
	}

}
