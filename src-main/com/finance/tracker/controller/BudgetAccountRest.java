package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.view.model.BudgetByAccount;
import com.finance.tracker.view.model.BudgetModel;
import com.google.gson.Gson;

@WebServlet("/budgetAccount")
public class BudgetAccountRest extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String type = (String) session.getAttribute("budgetAccount");
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<IBudget> budgets = new UserDAO().getAllBudgetsByUser(user.getUserId());
		if (type.equals("sum")) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			Collection<BudgetModel> makeJason = new HashSet<BudgetModel>();
			for (IBudget budget : budgets) {
				map.put(budget.getTitle(), (int) budget.getTotalAmount());
			}
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				String title = it.next();
				int sum = map.get(title);
				makeJason.add(new BudgetModel(title, sum));
			}
			response.getWriter().println(new Gson().toJson(makeJason));
		}
		if (type.equals("acc")) {
			Collection<IAccount> accounts = (List<IAccount>) new UserDAO().getAllAccounts(user.getUserId());
			Map<IAccount, Integer> map = new HashMap<IAccount, Integer>();
			Collection<BudgetModel> makeJason = new HashSet<BudgetModel>();
			for (IBudget budget : budgets) {
				Collection<Account> accountsPerBudget = budget.getAllAccounts();
				for (IAccount a : accounts) {
					for (Account acc : accountsPerBudget) {
						if (acc.equals(a)) {
							if (!map.containsKey(a)) {
								map.put(a, 1);
							} else {
								int number = map.get(a);
								map.put(a, number + 1);
							}
						}
					}
				}
			}
			for (Iterator<IAccount> it = map.keySet().iterator(); it.hasNext();) {
				Account a = (Account) it.next();
				String title = a.getTitle();
				int times = map.get(a);
				makeJason.add(new BudgetModel(title, times));
			}
			response.getWriter().println(new Gson().toJson(makeJason));
		}
	}

}
