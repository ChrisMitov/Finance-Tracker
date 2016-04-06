package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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
import com.finance.tracker.view.model.FinanceOperationModel;
import com.google.gson.Gson;

@WebServlet("/showExpenses")
public class ShowExpensesServlet extends BaseServlet {
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
		Collection<Expense> expenses = new ArrayList<Expense>();
		for (IAccount account : accounts) {
			expenses.addAll(new FinanceOperationDao().getAllExpencesByAccount((Account) account));
		}
		Collection<FinanceOperationModel> products = new HashSet<FinanceOperationModel>();
		for (IFinanceOperation expense : expenses) {
			if (products.isEmpty()) {
				products.add(expense.getViewModelFinanceOperation(expense.getSum(), expense.getCategory().getName()));
			} else {
				for (FinanceOperationModel model : products) {
					if (model.getName().equals(expense.getCategory().getName())) {
						model.setSum(model.getSum() + expense.getSum());
					} else {
						products.add(expense.getViewModelFinanceOperation(expense.getSum(),
								expense.getCategory().getName()));
					}
				}
			}
		}
		response.getWriter().println(new Gson().toJson(products));
	}
}