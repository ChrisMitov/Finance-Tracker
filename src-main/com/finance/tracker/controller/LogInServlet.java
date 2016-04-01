package com.finance.tracker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.dao.LogInDAO;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LogInDAO logIn = new LogInDAO();
		response.setContentType("text/html");

		if (logIn.validate(username, password)) {
			request.getSession().setAttribute("user", username);
			response.sendRedirect("./jsp/Register.jsp");
		} else {
			response.sendRedirect("./jsp/BadLogIn.html");
		}

	}

}
