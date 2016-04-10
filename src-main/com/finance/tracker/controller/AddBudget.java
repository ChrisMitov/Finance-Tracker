package com.finance.tracker.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.model.dao.UserDAO;

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
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
		request.setAttribute("accounts", accounts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/AddBudget.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		// if (validateAdd(request, response)) {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		String title = request.getParameter("title");
		double sum = 0;
		Date date = DEFAULT_DATE;
		Date date2 = DEFAULT_DATE;
		String calendar = request.getParameter("start");
		String end = request.getParameter("end");
		String selects[] = request.getParameterValues("selected");
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(calendar);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(end);
			IBudget budget = new Budget(title, sum, date, date2, user);
			for (int select = 0; select < selects.length; select++) {
				int idAcc = Integer.parseInt(selects[select]);
				Account a = (Account) new AccountDAO().getAccount(idAcc);
				budget.addAccount(a);
				sum += new AccountDAO().getAccount(idAcc).getSum();
			}
			if (checkDates(date, date2)) {
				request.setAttribute("dates", "Start date must be before end date!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/AddBudget.jsp");
				dispatcher.forward(request, response);
			}
			budget.setTotalAmount(sum);
			new BudgetDao().addBudget(budget);

		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}

		response.sendRedirect("./budget");
		// return;

		// } else {
		// request.setAttribute("emptyField", "All fields are obligatory!");
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("./jsp/AddBudget.jsp");
		// dispatcher.forward(request, response);
		// }

	}

	private boolean validateAdd(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String sum = request.getParameter("sum");
		String calendar = request.getParameter("start");
		// String type = request.getParameter("repeat");
		String selects[] = request.getParameterValues("selected");
		String end = request.getParameter("end");
		if (title == null || title.equals("")) {
			return false;
		}
		if (sum == null || sum.equals("")) {
			return false;
		}
		if (calendar == null || calendar.equals("") && end == null || end.equals("")) {
			return false;
		}

		if (!checkSelect(selects)) {
			return false;
		} else {
			return true;
		}

	}

	private boolean checkType(String type) {
		if (type.equals("NO_REPEAT")) {
			return true;
		}
		return false;
	}

	private boolean checkSelect(String select[]) {
		for (int index = 0; index < select.length; index++) {
			if (!select[index].equals("") || select[index] != null) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDates(Date d, Date d2) {
		return (d.after(d2));
	}
}
