package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<IAccount> accounts = new UserDAO().getAllAccounts(user.getUserId());
		Collection<Income> incomes = new ArrayList<Income>();
		for (IAccount account : accounts) {
			incomes.addAll(new FinanceOperationDao().getAllIncomeByAccount((Account) account));
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		Collection<FinanceOperationModel> products = new HashSet<FinanceOperationModel>();
		for (IFinanceOperation income : incomes) {
			if (map.containsKey(income.getCategory().getName())) {
				int number = map.get(income.getCategory().getName());
				map.replace(income.getCategory().getName(), number + income.getSum());
			} else {
				map.put(income.getCategory().getName(), income.getSum());
			}
		}
		for (Entry<String, Integer> entry : map.entrySet()) {
			products.add(new FinanceOperationModel(entry.getValue(), entry.getKey()));
		}
		response.getWriter().println(new Gson().toJson(products));
	}
}
