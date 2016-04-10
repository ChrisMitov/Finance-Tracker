package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/charts")
public class BudgetChartToDisplayServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		String display = request.getParameter("display");
		System.out.println(display+"!!!");
		session.setAttribute("display", display);
		if (display.equals("sum")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Budget.jsp");
			dispatcher.forward(request, response);
		}
		if (display.equals("acc")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/BudgetOnAccount.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
