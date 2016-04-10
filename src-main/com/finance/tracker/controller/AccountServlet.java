package com.finance.tracker.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Income;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.FinanceOperationDao;

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
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		String operationsAccount = request.getParameter("operationsAccount");
		if(operationsAccount != null){
			request.setAttribute("operationsAccount", operationsAccount);
		}
		Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
		for (IAccount account : accounts) {
			int balance = account.getSum();
			balance = balanceIncomes(calendar, day, month, year, account, balance);
			balance = balanceExpenses(calendar, day, month, year, account, balance);
			try {
				account.setSum(balance);
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("accounts", accounts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/account.jsp");
		dispatcher.forward(request, response);
	}

	private int balanceIncomes(Calendar calendar, int day, int month, int year, IAccount account, int balance) {
		Collection<Income> incomes = new FinanceOperationDao().getAllIncomeByAccount((Account) account);
		for (IFinanceOperation income : incomes) {
			calendar.setTime(income.getDate());
			if(calendar.get(Calendar.DAY_OF_MONTH) <= day && calendar.get(Calendar.MONTH) <= month && calendar.get(Calendar.YEAR) <= year){
				balance += income.getSum();
			}
		}
		return balance;
	}

	private int balanceExpenses(Calendar calendar, int day, int month, int year, IAccount account, int balance) {
		Collection<Expense> expenses = new FinanceOperationDao().getAllExpencesByAccount((Account) account);
		for (IFinanceOperation expense : expenses) {
			calendar.setTime(expense.getDate());
			if(calendar.get(Calendar.DAY_OF_MONTH) <= day && calendar.get(Calendar.MONTH) <= month && calendar.get(Calendar.YEAR) <= year){
				balance -= expense.getSum();
			}
		}
		return balance;
	}

}
