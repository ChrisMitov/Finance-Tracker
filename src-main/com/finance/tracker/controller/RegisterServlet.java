package com.finance.tracker.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.exception.PasswordException;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.validation.HashPassword;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String hashPassword = new HashPassword().hashPassword(password);

		IUserDAO userDAO = new UserDAO();
		// checking if there is user with this email
		if (userDAO.isUserExisting(email)) {
			request.setAttribute("emailError", "This email is already taken!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
			dispatcher.forward(request, response);
		} else {
			// checking if password meets criteria;
			if (!new HashPassword().isPasswordSecured(password)) {
				request.setAttribute("passwordError",
						"Password must contains a small letter, a capitale letter and a figure!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
				dispatcher.forward(request, response);
			} else {
				// checking if passwords match
				if (!password.equals(password2)) {

					request.setAttribute("passwordMissmatch", "Passwords are different!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
					dispatcher.forward(request, response);

				} else {
					IUser user = null;
					try {
						user = new User(firstName, lastName, hashPassword, email);
					} catch (FinanceTrackerException | PasswordException e) {
						e.printStackTrace();
					}
					userDAO.createUser(user);
					request.getRequestDispatcher("./jsp/LogIn.jsp").forward(request, response);
				}
			}
		}

	}

}
