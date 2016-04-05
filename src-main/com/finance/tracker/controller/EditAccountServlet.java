package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.dao.AccountDAO;

/**
 * Servlet implementation class EditAccountServlet
 */
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
		request.setAttribute("account", account);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/editAccount.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int accountId = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("name");
		int sum =  Integer.parseInt(request.getParameter("sum"));
		try {
			IAccount account = new AccountDAO().getAccount(accountId);
			account.setTitle(title);
			account.setSum(sum);
			new AccountDAO().updateAccount(account);;
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./account");
	}

}
