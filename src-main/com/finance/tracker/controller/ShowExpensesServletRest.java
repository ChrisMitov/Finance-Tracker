package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

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
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.view.model.FinanceOperationModel;
import com.google.gson.Gson;

@WebServlet("/showExpenses")
public class ShowExpensesServletRest extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
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
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		if (session.getAttribute("month") != null) {
			month = (int) session.getAttribute("month");
		}
		if (session.getAttribute("year") != null) {
			year = (int) session.getAttribute("year");
		}
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<IAccount> accounts = new UserDAO().getAllAccounts(user.getUserId());
		Collection<IFinanceOperation> expenses = new ArrayList<IFinanceOperation>();
		getExpenses(calendar, session, month, year, accounts, expenses);
		Map<String, Integer> map = new HashMap<String, Integer>();
		Collection<FinanceOperationModel> products = new HashSet<FinanceOperationModel>();
		getExpenses(calendar, month, year, expenses, map);
		writeToModel(map, products);
		response.getWriter().println(new Gson().toJson(products));
	}

	private void getExpenses(Calendar calendar, int month, int year, Collection<IFinanceOperation> expenses,
			Map<String, Integer> map) {
		for (IFinanceOperation expense : expenses) {
			calendar.setTime(expense.getDate());
			if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
				if (map.containsKey(expense.getCategory().getName())) {
					int number = map.get(expense.getCategory().getName());
					map.replace(expense.getCategory().getName(), number + expense.getSum());
				} else {
					map.put(expense.getCategory().getName(), expense.getSum());
				}
			}
		}
	}

	private void writeToModel(Map<String, Integer> map, Collection<FinanceOperationModel> products) {
		for (Entry<String, Integer> entry : map.entrySet()) {
			products.add(new FinanceOperationModel(entry.getValue(), entry.getKey()));
		}
	}

	private void getExpenses(Calendar calendar, HttpSession session, int month, int year, Collection<IAccount> accounts,
			Collection<IFinanceOperation> expenses) {
		if ((int) session.getAttribute("accountIdExpense") != 0) {
			int accountId = (int) session.getAttribute("accountIdExpense");
			IAccount account = new AccountDAO().getAccount(accountId);
			Collection<Expense> tmpExpenses = new FinanceOperationDao().getAllExpencesByAccount((Account) account);
			insertExpences(calendar, month, year, expenses, tmpExpenses);
		} else {
			for (IAccount account : accounts) {
				Collection<Expense> tmpExpenses = new FinanceOperationDao().getAllExpencesByAccount((Account) account);
				insertExpences(calendar, month, year, expenses, tmpExpenses);
			}
		}
	}

	private void insertExpences(Calendar calendar, int month, int year, Collection<IFinanceOperation> expenses,
			Collection<Expense> tmpExpenses) {
		for (IFinanceOperation expense : tmpExpenses) {
			calendar.setTime(expense.getDate());
			if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
				expenses.add(expense);
			}
		}
	}
}
