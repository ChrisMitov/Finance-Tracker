package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.exception.PasswordException;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.validation.HashPassword;

@WebServlet("/editprofile")
public class EditProfile extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// getting all data for current user if needed to be changed
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}
		request.getRequestDispatcher("./jsp/LogIn.jsp").forward(request, response);
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("userId");
		IUserDAO user = new UserDAO();
		session.setAttribute("currentUser", user);
		session.setAttribute("userName", user.getFirstNameById(id));
		session.setAttribute("lastName", user.getLastNameById(id));
		session.setAttribute("email", user.getEmailById(id));
		session.setAttribute("password", user.getPasswordById(id));
		session.setAttribute("currency", user.getCurrencyById(id));
		session.setAttribute("startDate", user.getDateByID(id));
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
	private IUser validateUpdates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("newFirstName");
		String lastName = request.getParameter("newLastName");
		String password = request.getParameter("newPassword");
		String email = request.getParameter("newEemail");
		String currency = request.getParameter("newCurrency");

		HttpSession session = request.getSession();
		IUser userToUpdate = (IUser) session.getAttribute("currentUser");
		System.out.println(userToUpdate.getFirstName());
		if (firstName != null && firstName.length() > 0) {
			userToUpdate.setFirstName(firstName);
		}
		if (lastName != null && lastName.length() > 0) {
			userToUpdate.setLastName(lastName);
		}
		if (email != null && email.length() > 0) {
			userToUpdate.setEmail(email);
		}
		if (password != null) {
			try {
				if (new HashPassword().isPasswordSecured(password)) {
					String hashedPassword = new HashPassword().hashPassword(password);
					userToUpdate.setPassword(hashedPassword);
				} else {
					request.setAttribute("passwordError",
							"Password must contains a small letter, a capitale letter and a figure!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
					dispatcher.forward(request, response);
				}
			} catch (FinanceTrackerException e) {
				request.setAttribute("passwordError", e.getMessage());
			}
		}
		if (!(currency.equals("blanck"))) {
			Currency newCurrency = Currency.valueOf(currency);
			userToUpdate.setCurrency(newCurrency);
		}

		return userToUpdate;
	}

}
