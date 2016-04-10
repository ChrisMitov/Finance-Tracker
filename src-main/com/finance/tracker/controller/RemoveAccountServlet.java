package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.FinanceOperationDao;

@WebServlet("/accountRemove")
public class RemoveAccountServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int accountId = Integer.parseInt(request.getParameter("id"));
		IAccount account = new AccountDAO().getAccount(accountId);
		Collection<IFinanceOperation> operations = new LinkedList<IFinanceOperation>();
		operations.addAll(new FinanceOperationDao().getAllExpencesByAccount((Account) account));
		operations.addAll(new FinanceOperationDao().getAllIncomeByAccount((Account) account));
		if(!operations.isEmpty()){
			request.setAttribute("operationsAccount", "First delete all finance operations");
			ServletContext servletContext = getServletContext();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/account");
			requestDispatcher.forward(request, response);
			return;
		}
		new AccountDAO().deleteAccount(accountId);
		response.sendRedirect("./account");
	}

}
