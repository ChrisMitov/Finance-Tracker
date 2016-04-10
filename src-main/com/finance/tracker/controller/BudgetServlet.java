package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.model.dao.UserDAO;

@WebServlet("/budget")
public class BudgetServlet extends BaseServlet {
	// private static final int DAY = 1;
	// private static final int DAYS_IN_MONTH = 7;
	// private static final int DAYS_IN_YEAR = 365;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		checkBudgets(user);
		Collection<Budget> budgets = new BudgetDao().getAllBudgetsByUser(user);
		Map<Budget, Integer> sumsPerDay = new TreeMap<Budget, Integer>();
		Map<Budget, Collection<Account>> accounts = getMap(budgets);
		session.setAttribute("accounts", accounts);

		for (Budget b : budgets) {
			checkBalances(b);
			int days = dateDifference(b.getStartDate(), b.getEndDate()) + 1;
			sumsPerDay.put(b, (int) (b.getTotalAmount() / days));
		}
		// request.setAttribute("budgets", budgets);
		request.setAttribute("sums", sumsPerDay);
		if (budgets.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/BlanckBudget.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Budget.jsp");
			dispatcher.forward(request, response);
		}

	}

	private int dateDifference(Date date1, Date date2) {
		long diff = date2.getTime() - date1.getTime();
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	private Map<Budget, Collection<Account>> getMap(Collection<Budget> budgets) {
		Map<Budget, Collection<Account>> accounts = new TreeMap<Budget, Collection<Account>>();
		for (Budget b : budgets) {
			Collection<Account> a = b.getAllAccounts();
			accounts.put(b, a);

		}
		return accounts;
	}

	private void checkBudgets(User u) {
		Collection<IBudget> budgets = new UserDAO().getAllBudgetsByUser(u.getUserId());
		Date now = new Date();
		for (IBudget b : budgets) {
			if (b.getEndDate().before(now)) {
				try {
					u.deleteBudget(b);
				} catch (FinanceTrackerException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void checkBalances(IBudget budget) {
		double sum = 0.0;
		Collection<Account> accounts = new BudgetDao().foundBudgetByTitle(budget.getTitle()).getAllAccounts();
		for(Account a:accounts){
			sum+=a.getSum();
		}
		budget.setTotalAmount(sum);
		new BudgetDao().updateBudget(budget);
	}
}
