
package com.finance.tracker.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.Currency;
import com.finance.tracker.model.ExchangeRate;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.dao.CurrencyDAO;

import com.finance.tracker.model.dao.LogInDAO;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.validation.HashPassword;

@WebServlet("/login")
public class LogInServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}
		request.getRequestDispatcher("./jsp/LogIn.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDAO userDao = new UserDAO();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassowrd = new HashPassword().hashPassword(password);

		if (new LogInDAO().validate(username, hashedPassowrd)) {
			HttpSession session = request.getSession();
			// session.setMaxInactiveInterval(-1);
			int userId = userDao.getUserId(username);
			session.setAttribute("userId", userId);

			IUser user = userDao.getUser(userId);
			
			try {
				List<Currency> toConvert = getCurrencies(user);
				Currency base = userCurrency(user);
				ExchangeRate rate = new CurrencyDAO().getManyRates(toConvert, base);
				Map<Currency, Double> rates = rate.getManyResults();
				request.getSession().setAttribute("base", base);
				request.getSession().setAttribute("rates", rates);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			session.setAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME, user);
			session.setAttribute("userName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("currency", user.getCurrency());
			session.setAttribute("startDate", user.getJointedDate());
			Calendar calendar = Calendar.getInstance();
			session.setAttribute("month", calendar.get(Calendar.MONTH) + 1);
			session.setAttribute("year", calendar.get(Calendar.YEAR));
			session.setAttribute("accountIdExpense", 0);
			session.setAttribute("budgetAccount", "sum");
			response.sendRedirect("./");
		} else {
			request.setAttribute("wrongUser", "Incorrect email or password!");
			request.getRequestDispatcher("./jsp/LogIn.jsp").forward(request, response);
		}

	}
	
	private Currency userCurrency(IUser user){
		return user.getCurrency();
	}

	private List<Currency> getCurrencies(IUser user){
		List<Currency> all = new ArrayList<Currency>();
		all.add(Currency.BGN);
		all.add(Currency.USD);
		all.add(Currency.EUR);
		List<Currency> toReturn = new ArrayList<>();
		for(Currency c:all){
			if(!c.equals(user.getCurrency())){
				toReturn.add(c);
			}
		}
		return toReturn;
		
	}


}
