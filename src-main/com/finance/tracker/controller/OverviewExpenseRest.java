package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.view.model.ExpenseModel;
import com.google.gson.Gson;

/**
 * Servlet implementation class OverviewExpenseRest
 */
@WebServlet("/overviewExpense")
public class OverviewExpenseRest extends BaseServlet {
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
		for (IAccount account : accounts) {
			expenses.addAll(new FinanceOperationDao().getAllExpencesByAccount((Account) account));
		}
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		int monthMaxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i <= monthMaxDays; i++) {
			result.put(i, 0);
		}
		for (IFinanceOperation expense : expenses) {
			calendar.setTime(expense.getDate());
			if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
				if (result.containsKey(calendar.get(Calendar.DAY_OF_MONTH)))
					result.replace(calendar.get(Calendar.DAY_OF_MONTH),
							result.get(calendar.get(Calendar.DAY_OF_MONTH)) + expense.getSum());
			}
		}
		Collection<ExpenseModel> products = new LinkedList<ExpenseModel>();
		for (Entry<Integer, Integer> entry : result.entrySet()) {
			products.add(new ExpenseModel(String.valueOf(entry.getKey()), entry.getValue()));
		}
		Collections.sort((List<ExpenseModel>) products, new Comparator<ExpenseModel>() {
			@Override
			public int compare(ExpenseModel s1, ExpenseModel s2) {

				Integer val1 = Integer.parseInt(s1.getDay());
				Integer val2 = Integer.parseInt(s2.getDay());
				return val1.compareTo(val2);
			}
		});
		response.getWriter().println(new Gson().toJson(products));
	}
}