package com.finance.tracker.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.BudgetDao;

@WebServlet("/addBudget")
public class AddBudget extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final Date DEFAULT_DATE = new Date();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/AddBudget.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		String title = request.getParameter("title");
		double sum = Double.parseDouble(request.getParameter("sum"));
		Date date = DEFAULT_DATE;
		try {

			date = formatter.parse(request.getParameter("start"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String type = request.getParameter("repeat");
		try {
			IBudget budget = new Budget(title, sum, date, RepeatType.valueOf(type), user);
			new BudgetDao().addBudget(budget);
			user.addBudget(budget);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}

		response.sendRedirect("./budget");

	}

}
