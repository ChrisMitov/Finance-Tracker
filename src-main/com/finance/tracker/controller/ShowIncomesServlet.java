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
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Income;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.view.model.FinanceOperationModel;
import com.google.gson.Gson;

@WebServlet("/showIncome")
public class ShowIncomesServlet extends BaseServlet {
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
		Collection<IFinanceOperation> incomes = new ArrayList<IFinanceOperation>();
		getIncomes(calendar, session, month, year, accounts, incomes);
		Map<String, Integer> map = new HashMap<String, Integer>();
		Collection<FinanceOperationModel> products = new HashSet<FinanceOperationModel>();
		filterIncomes(calendar, month, year, incomes, map);
		writeToModel(map, products);
		response.getWriter().println(new Gson().toJson(products));
	}

	private void writeToModel(Map<String, Integer> map, Collection<FinanceOperationModel> products) {
		for (Entry<String, Integer> entry : map.entrySet()) {
			products.add(new FinanceOperationModel(entry.getValue(), entry.getKey()));
		}
	}

	private void getIncomes(Calendar calendar, HttpSession session, int month, int year, Collection<IAccount> accounts,
			Collection<IFinanceOperation> incomes) {
		if ((int) session.getAttribute("accountIdExpense") != 0) {
			int accountId = (int) session.getAttribute("accountIdExpense");
			IAccount account = new AccountDAO().getAccount(accountId);
			Collection<Income> tmpIncomes = new FinanceOperationDao().getAllIncomeByAccount((Account) account);
			insertIncomes(calendar, month, year, incomes, tmpIncomes);
		} else {
			for (IAccount account : accounts) {
				Collection<Income> tmpIncomes = new FinanceOperationDao().getAllIncomeByAccount((Account) account);
				insertIncomes(calendar, month, year, incomes, tmpIncomes);
			}
		}
	}

	private void filterIncomes(Calendar calendar, int month, int year, Collection<IFinanceOperation> incomes,
			Map<String, Integer> map) {
		for (IFinanceOperation income : incomes) {
			calendar.setTime(income.getDate());
			if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
				if (map.containsKey(income.getCategory().getName())) {
					int number = map.get(income.getCategory().getName());
					map.replace(income.getCategory().getName(), number + income.getSum());
				} else {
					map.put(income.getCategory().getName(), income.getSum());
				}
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
