package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;

@WebServlet("/account")
public class AccountServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/account.jsp");
		dispatcher.forward(request, response);
	}

}
