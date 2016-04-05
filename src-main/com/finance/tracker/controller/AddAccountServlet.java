package com.finance.tracker.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.UserDAO;

/**
 * Servlet implementation class AddAccountServlet
 */
@WebServlet("/addAccount")
public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// if is autenticated
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/addAccount.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new UserDAO().getUser(101);
		String title = request.getParameter("name");
		int sum =  Integer.parseInt(request.getParameter("sum"));
		try {
			IAccount account = new Account(title, sum, user);
			new AccountDAO().createAccount(account);
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./account");
		
	}

}
