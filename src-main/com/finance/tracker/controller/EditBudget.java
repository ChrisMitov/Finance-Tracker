package com.finance.tracker.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.model.dao.IBudgetDao;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.validation.HashPassword;

@WebServlet("/editBudget")
public class EditBudget extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME) == null) {
			response.sendRedirect("./login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("budgetid"));
		IBudget budget = new BudgetDao().foundById(id);
		session.setAttribute("budgetId", id);
		session.setAttribute("budget", budget);
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/editBudget.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		IBudgetDao budget = new BudgetDao();
		IBudget budgetToUpdate = validateUpdates(request, response);
		budget.updateBudget(budgetToUpdate);
		response.sendRedirect("./budget");
	}

	private IBudget validateUpdates(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("budgetId");
		String sum = request.getParameter("newSum");
		String date = request.getParameter("newStart");
		String title = (String) request.getParameter("newTitle");
		String type = (String) request.getParameter("newRepeat");
		IBudget budget = (IBudget) request.getSession().getAttribute("budget");
		if (sum != null && sum.length() > 0 && !sum.equals("")) {
			double newSum = Double.parseDouble(sum);
			budget.setTotalAmount(newSum);
		}

		if (!(date.equals("")) && date != null && date.length() > 0) {
			try {
				Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				budget.setStartDate(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		} else if (title != null && title.length() > 0 && !title.equals("")) {
			try {
				budget.setTitle(title);
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		} else if (!(type.equals("blanck"))) {
			RepeatType newType = RepeatType.valueOf(type);
			System.out.println(newType+"!!");
			budget.setRepeatType(newType);
		}

		budget.setId(id);
		return budget;
	}
}
