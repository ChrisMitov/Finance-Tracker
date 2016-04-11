package com.finance.tracker.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

/**
 * Servlet implementation class MyAddAccount
 */
//@WebServlet("/MyAddBudget")
//public class MyAddAccount extends BaseServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		if (!super.isAuthenticated(request)) {
//			response.sendRedirect("./login");
//			return;
//		}
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
//		Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
//		request.setAttribute("accounts", accounts);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/AddBudget.jsp");
//		dispatcher.forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		if (!super.isAuthenticated(request)) {
//			response.sendRedirect("./login");
//			return;
//		}
//		String title = request.getParameter("title");
//		Integer sum = Integer.valueOf(request.getParameter("sum"));
//		String start = request.getParameter("start");
//		String selects[] = request.getParameterValues("selected");
//		String end = request.getParameter("end");
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
//		Calendar calendar = Calendar.getInstance();
//		try {
//			Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(start);
//			Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(end);
//			if (dateStart.before(dateEnd)) {
//				IBudget budget = new Budget();
//				budget.setTitle(title);
//				budget.setRepeatType(RepeatType.NO_REPEAT);
//				budget.setTotalAmount(sum);
//				budget.setStartDate(dateStart);
//				budget.setEndDate(dateEnd);
//				budget.setUser(user);
//				if (selects.length > 0) {
//					for (String string : selects) {
//						budget.addAccount((Account) new AccountDAO().getAccount(Integer.valueOf(string)));
//					}
//				}
//				new BudgetDao().addBudget(budget);
//				response.sendRedirect("./budget");
//				return;
//			} else {
//				Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
//				request.setAttribute("accounts", accounts);
//				request.setAttribute("emptyField", "All fields are obligatory!");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/AddBudget.jsp");
//				dispatcher.forward(request, response);
//			}
//		} catch (ParseException | FinanceTrackerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//}
