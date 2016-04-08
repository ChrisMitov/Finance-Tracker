package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Income;
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
		Calendar calendar = Calendar.getInstance();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		if (session.getAttribute("month") != null) {
			month = (int) session.getAttribute("month");
		}
		if (session.getAttribute("year") != null) {
			year = (int) session.getAttribute("year");
		}
		Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
		Collection<IFinanceOperation> incomes = new ArrayList<>();
		getIncomes(calendar, session, month, year, accounts, incomes);
		request.setAttribute("month", month);
		request.setAttribute("year", year);
		request.setAttribute("accounts", accounts);
		if (incomes.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/blankIncome.jsp");
			dispatcher.forward(request, response);
			return;
		}
		Collections.sort((List<IFinanceOperation>) incomes, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
		request.setAttribute("currency", user.getCurrency());
		request.setAttribute("incomes", incomes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/incomes.jsp");
		dispatcher.forward(request, response);
	}

	private void getIncomes(Calendar calendar, HttpSession session, int month, int year, Collection<IAccount> accounts,
			Collection<IFinanceOperation> incomes) {
		if ((int) session.getAttribute("accountIdExpense") != 0) {
			int accountId = (int) session.getAttribute("accountIdExpense");
			IAccount account = new AccountDAO().getAccount(accountId);
			Collection<Income> tmpIncome = new FinanceOperationDao().getAllIncomeByAccount((Account) account);
			insertIncomes(calendar, month, year, incomes, tmpIncome);
		} else {
			for (IAccount account : accounts) {
				Collection<Income> tmpIncome = new FinanceOperationDao().getAllIncomeByAccount((Account) account);
				insertIncomes(calendar, month, year, incomes, tmpIncome);
			}
		}
	}

	private void insertIncomes(Calendar calendar, int month, int year, Collection<IFinanceOperation> incomes,
			Collection<Income> tmpIncome) {
		for (IFinanceOperation income : tmpIncome) {
			calendar.setTime(income.getDate());
			if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
				incomes.add(income);
			}
		}
	}

}
