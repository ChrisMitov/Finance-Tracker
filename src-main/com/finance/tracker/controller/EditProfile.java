package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.PasswordException;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// getting all data for current user if needed to be changed
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			response.sendRedirect("../jsp/LogIn.jsp");
			return;
		}
		int id = (int) session.getAttribute("userId");
		IUserDAO user = new UserDAO();
		session.setAttribute("currentUser", user);
		session.setAttribute("userName", user.getFirstNameById(id));
		session.setAttribute("lastName", user.getLastNameById(id));
		session.setAttribute("email", user.getEmailById(id));
		session.setAttribute("password", user.getPasswordById(id));
		session.setAttribute("currency", user.getCurrencyById(id));
		session.setAttribute("startDate", user.getDateByID(id));
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/EditProfile.jsp");
		rd.forward(request, response);
	}

	// update user in database
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IUserDAO user = new UserDAO();
		IUser userToUpdate = validateUpdates(request);
		user.updateUser(userToUpdate);
		doGet(request, response);

	}

	// checks what information the user has changed
	private IUser validateUpdates(HttpServletRequest request) {
		String firstName = request.getParameter("newFirstName");
		System.out.println("FIRST NAME " + firstName);
		String lastName = request.getParameter("newLastName");
		String password = request.getParameter("newPpassword");
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
				userToUpdate.setPassword(password);
			} catch (PasswordException e) {
				request.setAttribute("passwordError", e.getMessage());
			}
		}
		if (!(session.getAttribute("currency").equals("blanck"))) {
			Currency newCurrency = Currency.valueOf(currency);
			userToUpdate.setCurrency(newCurrency);
		}

		return userToUpdate;
	}

}
