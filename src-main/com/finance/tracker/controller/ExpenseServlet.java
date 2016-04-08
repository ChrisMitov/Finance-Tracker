package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.Account;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.FinanceOperationDao;

/**
 * Servlet implementation class ExpenseServlet
 */
@WebServlet("/expenses")
public class ExpenseServlet extends BaseServlet {
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
		Collection<IFinanceOperation> expenses = new LinkedList<IFinanceOperation>();
		getExpenses(calendar, session, month, year, accounts, expenses);
		request.setAttribute("month", month);
		request.setAttribute("year", year);
		request.setAttribute("accounts", accounts);
		if (expenses.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/blankExpense.jsp");
			dispatcher.forward(request, response);
			return;
		}
		request.setAttribute("currency", user.getCurrency());
		request.setAttribute("expenses", expenses);
		Collections.sort((List<IFinanceOperation>) expenses, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/expences.jsp");
		dispatcher.forward(request, response);
	}

	private void getExpenses(Calendar calendar, HttpSession session, int month, int year, Collection<IAccount> accounts,
			Collection<IFinanceOperation> expenses) {
		if ((int) session.getAttribute("accountIdExpense") != 0) {
			int accountId = (int) session.getAttribute("accountIdExpense");
			IAccount account = new AccountDAO().getAccount(accountId);
			Collection<Expense> tmpExpenses = new FinanceOperationDao().getAllExpencesByAccount((Account) account);
			insertExpenses(calendar, month, year, expenses, tmpExpenses);
		} else {
			for (IAccount account : accounts) {
				Collection<Expense> tmpExpenses = new FinanceOperationDao().getAllExpencesByAccount((Account) account);
				insertExpenses(calendar, month, year, expenses, tmpExpenses);
			}
		}
	}

	private void insertExpenses(Calendar calendar, int month, int year, Collection<IFinanceOperation> expenses,
			Collection<Expense> tmpExpenses) {
		for (IFinanceOperation expense : tmpExpenses) {
			calendar.setTime(expense.getDate());
			if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
				expenses.add(expense);
			}
		}
	}
}
