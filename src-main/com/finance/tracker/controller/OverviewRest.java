package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.Account;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Income;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.view.model.OverviewModel;
import com.google.gson.Gson;

/**
 * Servlet implementation class OverviewRest
 */
@WebServlet("/overviewRest")
public class OverviewRest extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Calendar calendar = Calendar.getInstance();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		calendar.setTime(new Date());
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		if (session.getAttribute("month") != null) {
			month = (int) session.getAttribute("month");
		}
		if (session.getAttribute("year") != null) {
			year = (int) session.getAttribute("year");
		}
		Collection<IAccount> accounts = new UserDAO().getAllAccounts(user.getUserId());
		Collection<Expense> expenses = new LinkedList<Expense>();
		Collection<Income> incomes = new LinkedList<Income>();
		getAllFinanceOperations(accounts, expenses, incomes);
		Map<Integer, Integer> mapExpenses = new HashMap<Integer, Integer>();
		Map<Integer, Integer> mapIncomes = new HashMap<Integer, Integer>();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		int monthMaxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		addDays(mapExpenses, mapIncomes, monthMaxDays);
		insertMaps(calendar, month, year, expenses, mapExpenses);
		insertIncomes(calendar, month, year, incomes, mapIncomes);
		Collection<OverviewModel> overview = new LinkedList<OverviewModel>();
		writeToModel(mapExpenses, mapIncomes, monthMaxDays, overview);
		response.getWriter().println(new Gson().toJson(overview));
	}

	private void getAllFinanceOperations(Collection<IAccount> accounts, Collection<Expense> expenses,
			Collection<Income> incomes) {
		for (IAccount account : accounts) {
			expenses.addAll(new FinanceOperationDao().getAllExpencesByAccount((Account) account));
			incomes.addAll(new FinanceOperationDao().getAllIncomeByAccount((Account) account));
		}
	}

	private void writeToModel(Map<Integer, Integer> mapExpenses, Map<Integer, Integer> mapIncomes, int monthMaxDays,
			Collection<OverviewModel> overview) {
		for (int i = 1; i <= monthMaxDays; i++) {
			int income = 0;
			int expense = 0;
			if (mapExpenses.containsKey(i)) {
				expense = mapExpenses.get(i);
			}
			if (mapIncomes.containsKey(i)) {
				income = mapIncomes.get(i);
			}
			overview.add(new OverviewModel(String.valueOf(i), income, expense));
		}
	}

	private void addDays(Map<Integer, Integer> mapExpenses, Map<Integer, Integer> mapIncomes, int monthMaxDays) {
		for (int i = 1; i <= monthMaxDays; i++) {
			mapExpenses.put(i, 0);
			mapIncomes.put(i, 0);
		}
	}

	private void insertIncomes(Calendar calendar, int month, int year, Collection<Income> incomes,
			Map<Integer, Integer> mapIncomes) {
		for (IFinanceOperation income : incomes) {
			calendar.setTime(income.getDate());
			if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
				if (mapIncomes.containsKey(calendar.get(Calendar.DAY_OF_MONTH)))
					mapIncomes.replace(calendar.get(Calendar.DAY_OF_MONTH),
							mapIncomes.get(calendar.get(Calendar.DAY_OF_MONTH)) + income.getSum());
			}
		}
	}

	private void insertMaps(Calendar calendar, int month, int year, Collection<Expense> expenses,
			Map<Integer, Integer> mapExpenses) {
		for (IFinanceOperation expense : expenses) {
			calendar.setTime(expense.getDate());
			if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
				if (mapExpenses.containsKey(calendar.get(Calendar.DAY_OF_MONTH)))
					mapExpenses.replace(calendar.get(Calendar.DAY_OF_MONTH),
							mapExpenses.get(calendar.get(Calendar.DAY_OF_MONTH)) + expense.getSum());
			}
		}
	}
}
