package com.finance.tracker.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;


@WebServlet("/addAccount")
public class AddAccountServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/addAccount.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
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
