package com.finance.tracker.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.ExchangeRateConventor;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.validation.HashPassword;

@WebServlet("/editprofile")
public class EditProfile extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// getting all data for current user if needed to be changed
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME) == null) {
			response.sendRedirect("./login");
			return;
		}
		int id = (int) session.getAttribute("userId");
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		IUserDAO Dao = new UserDAO();
		session.setAttribute("user", user);
		session.setAttribute("userName", Dao.getFirstNameById(id));
		session.setAttribute("lastName", Dao.getLastNameById(id));
		session.setAttribute("email", Dao.getEmailById(id));
		session.setAttribute("password", Dao.getPasswordById(id));
		session.setAttribute("currency", Dao.getCurrencyById(id));
		session.setAttribute("startDate", Dao.getDateByID(id));
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/Profile.jsp");
		rd.forward(request, response);
	}

	// update user in database
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}

		IUserDAO user = new UserDAO();
		IUser userToUpdate = validateUpdates(request, response);
		user.updateUser(userToUpdate);
		doGet(request, response);
	}

	// checks what information the user has changed
	private IUser validateUpdates(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		String firstName = request.getParameter("newFirstName");
		String lastName = request.getParameter("newLastName");
		String password = request.getParameter("newPassword");
		String email = request.getParameter("newEmail");
		String currency = request.getParameter("newCurrency");

		HttpSession session = request.getSession();
		IUser userToUpdate = ((IUser) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME));

		if (firstName != null && firstName.length() > 0) {
			userToUpdate.setFirstName(firstName);
		} else if (lastName != null && lastName.length() > 0) {
			userToUpdate.setLastName(lastName);
		} else if (email != null && email.length() > 0) {
			userToUpdate.setEmail(email);
		} else if (password != null && password.length() > 0) {
			try {
				if (new HashPassword().isPasswordSecured(password)) {
					String hashedPassword = new HashPassword().hashPassword(password);
					userToUpdate.setPassword(hashedPassword);
				} else {
					request.setAttribute("passwordError",
							"Password must contains a small letter, a capitale letter and a figure!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Profile.jsp");
					dispatcher.forward(request, response);
				}
			} catch (FinanceTrackerException e) {
				request.setAttribute("passwordError", e.getMessage());
			}
		} else if (!(currency.equals("blanck"))) {
			Currency newCurrency = Currency.valueOf(currency);
			userToUpdate.setCurrency(newCurrency);
			ExchangeRateConventor rate = new ExchangeRateConventor(user, Currency.valueOf(currency));
			try {
				rate.covert();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return userToUpdate;
	}

}
