package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;


@WebServlet("/editAccount")
public class EditAccountServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		IAccount account = new AccountDAO().getAccount(id);
		HttpSession session = request.getSession();
		session.setAttribute("accountId", id);
		request.setAttribute("account", account);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/editAccount.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		int accountId = (int) session.getAttribute("accountId");
		System.err.println(accountId);
		String title = request.getParameter("name");
		int sum =  Integer.parseInt(request.getParameter("sum"));
		try {
			IAccount account = new AccountDAO().getAccount(accountId);
			account.setTitle(title);
			account.setSum(sum);
			account.setOwner(user);
			account.setId(accountId);
			new AccountDAO().updateAccount(account);;
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./account");
	}

}
