package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.view.model.BudgetModel;
import com.google.gson.Gson;

/**
 * Servlet implementation class ShowBudgetRest
 */
@WebServlet("/budgetChart")
public class ShowBudgetRest extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<IBudget> budgets = new UserDAO().getAllBudgetsByUser(user.getUserId());
		Map<String, Integer> map = new HashMap<String, Integer>();
		Collection<BudgetModel> makeJason = new HashSet<BudgetModel>();
		for(IBudget budget:budgets){
			map.put(budget.getTitle(), (int) budget.getTotalAmount());
		}
		for(Iterator <String> it = map.keySet().iterator(); it.hasNext();){
			String title = it.next();
			int sum = map.get(title);
			makeJason.add(new BudgetModel(title, sum));
		}
		response.getWriter().println(new Gson().toJson(makeJason));
	}

}
