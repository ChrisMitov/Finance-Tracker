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
		if (validateAdd(request, response)) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
			String title = request.getParameter("title");
			double sum = Double.parseDouble(request.getParameter("sum"));
			Date date = DEFAULT_DATE;
			String calendar = request.getParameter("start");
			String type = request.getParameter("repeat");
			String selects[] = request.getParameterValues("selected");
			List<IAccount> allAcounts = (List<IAccount>) new UserDAO().getAllAccounts(user.getUserId());
			List<IAccount> matches = new ArrayList<IAccount>();
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(calendar);
				IBudget budget = new Budget(title, sum, date, RepeatType.valueOf(type), user);
				for (int select = 0; select < selects.length; select++) {
					for (IAccount a : allAcounts) {
						if (selects[select].equals(a.getTitle())) {
							matches.add(a);
						}
					}
				}
				new BudgetDao().addAccounts(budget, matches);
				user.addBudget(budget);

			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
			if (checkType(type)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("./repeatBudget");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("./budget");
			}
		} else {
			request.setAttribute("emptyField", "All fields are obligatory!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/AddBudget.jsp");
			dispatcher.forward(request, response);
		}

	}

	private boolean validateAdd(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String sum = request.getParameter("sum");
		String calendar = request.getParameter("start");
		String type = request.getParameter("repeat");
		String selects[] = request.getParameterValues("selected");
		if (title == null || title.equals("")) {
			return false;
		}
		if (sum == null || sum.equals("")) {
			return false;
		}
		if (calendar == null || calendar.equals("")) {
			return false;
		}
		if (type.equals("") || type == null) {
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
}
