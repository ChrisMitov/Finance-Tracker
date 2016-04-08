package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/period")
public class PeriodServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		int month = Integer.parseInt(request.getParameter("month"));
		session.setAttribute("month", month);
		int year = Integer.parseInt(request.getParameter("year"));
		session.setAttribute("year", year);
		int accountIdExpense = Integer.parseInt(request.getParameter("fromAccount"));
		session.setAttribute("accountIdExpense", accountIdExpense);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./expenses");
		dispatcher.forward(request, response);
	}

}
