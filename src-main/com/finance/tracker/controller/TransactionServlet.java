package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.UserDAO;

@WebServlet("/transaction")
public class TransactionServlet extends BaseServlet {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/transaction.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int fromAccountId = Integer.parseInt(request.getParameter("fromAccount"));
		int toAccountId = Integer.parseInt(request.getParameter("toAccount"));
		int sum = Integer.parseInt(request.getParameter("sum"));
		IAccount fromAccount = new AccountDAO().getAccount(fromAccountId);
		IAccount toAccount = new AccountDAO().getAccount(toAccountId);
		if (fromAccount.getSum() >= sum || sum < 0) {
			try {
				fromAccount.setSum(fromAccount.getSum() - sum);
				toAccount.setSum(toAccount.getSum() + sum);
				new AccountDAO().updateAccount(fromAccount);
				new AccountDAO().updateAccount(toAccount);
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
			response.sendRedirect("./account");
		} else {
			IUser user = new UserDAO().getUser(101);
			Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
			request.setAttribute("accounts", accounts);
			request.setAttribute("wrongSum", "Not enough money in account");
			request.getRequestDispatcher("./jsp/transaction.jsp").forward(request, response);
		}
	}

}
