package com.finance.tracker.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.TagDao;

@WebServlet("/editExpense")
public class EditExpenseServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
		IFinanceOperation operation = new FinanceOperationDao().foundById(id);
		request.setAttribute("accounts", accounts);
		Collection<Category> categories = new CategoryDao().getAllCategoriesByUser(user);
		request.setAttribute("categories", categories);
		Collection<Tag> tags = new ArrayList<>();
		for (Category category : categories) {
			tags.addAll(new TagDao().getAllTagsByCategory(category));
		}
		request.setAttribute("tags", tags);
		request.setAttribute("expense", operation);
		session.setAttribute("expenseId", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/editExpense.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		int operationId = (int) session.getAttribute("expenseId");
		int sum = Integer.parseInt(request.getParameter("sum"));
		String description = request.getParameter("description");
		int accountId = Integer.parseInt(request.getParameter("account"));
		int catetogoryId = Integer.parseInt(request.getParameter("category"));
		String dat = request.getParameter("date");
		String[] tags = request.getParameterValues("tags");
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dat);
			IFinanceOperation operation = new FinanceOperationDao().foundById(operationId);
			operation.setSum(sum);
			operation.setDate(date);
			operation.setDescription(description);
			operation.setAccount((Account) new AccountDAO().getAccount(accountId));
			operation.setRepeatType(RepeatType.NO_REPEAT);
			operation.setCategory(new CategoryDao().foundById(catetogoryId));
			for (String string : tags) {
				operation.addTag(new TagDao().foundTagByName(string));
			}
			operation.setId(operationId);
			new FinanceOperationDao().updateFinanceOperation(operation);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./expenses");
	}

}
