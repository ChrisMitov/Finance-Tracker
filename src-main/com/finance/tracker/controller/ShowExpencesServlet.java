package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.model.Account;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.view.model.FinanceOperationModel;
import com.google.gson.Gson;

@WebServlet("/showExpences")
public class ShowExpencesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if is authenticated
		Collection<IAccount> accounts = new UserDAO().getAllAccounts(101);
		Collection<Expense> expences = new ArrayList<Expense>();
		for (IAccount account : accounts) {
			expences.addAll(new FinanceOperationDao().getAllExpencesByAccount((Account) account));
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Collection<FinanceOperationModel> products = new ArrayList<FinanceOperationModel>();
		for (IFinanceOperation expence : expences) {
			products.add(expence.getViewModelFinanceOperation(expence.getSum(), expence.getCategory().getName()));
		}
		response.getWriter().println(new Gson().toJson(products));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
