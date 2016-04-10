package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.BudgetDao;

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
		Collection<Budget> budgets = new BudgetDao().getAllBudgetsByUser(user);
		Map<Budget, Integer> sumsPerDay = new HashMap<Budget, Integer>();
		for (Budget b : budgets) {
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

}
