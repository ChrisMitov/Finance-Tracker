package com.finance.tracker.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.validation.Validation;

@WebServlet("/budget")
public class BudgetServlet extends BaseServlet {
	private static final int DAY = 1;
	private static final int DAYS_IN_MONTH = 7;
	private static final int DAYS_IN_YEAR = 365;
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
			int days = getNumberOfDay(b);
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

	private int getNumberOfDay(Budget b) {
		try {
			new Validation().validateNotNullObject(b);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		RepeatType type=b.getRepeatType();
		if (type.equals(RepeatType.DAILY)) {
			return DAY;
		}
		if (type.equals(RepeatType.MONTHLY)) {
			return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		if (type.equals(RepeatType.NO_REPEAT)) {
			return 1;
		}
		if (type.equals(RepeatType.WEEKLY)) {
			return DAYS_IN_MONTH;
		}
		else{
			return DAYS_IN_YEAR;
		}

	}
}
