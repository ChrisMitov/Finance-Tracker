package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OverviewPeriod
 */
@WebServlet("/overviewPeriod")
public class OverviewPeriod extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		int month = Integer.parseInt(request.getParameter("month"));
		session.setAttribute("month", month);
		int year = Integer.parseInt(request.getParameter("year"));
		session.setAttribute("year", year);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./");
		dispatcher.forward(request, response);
	}

	

}
