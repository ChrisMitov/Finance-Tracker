package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.exception.PasswordException;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.UserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/LogIn.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");

		if (password.equals(password2)) {

			IUser user=null;
			try {
				user = new User(firstName, lastName, password, email);
			} catch (PasswordException e) {
				request.setAttribute("passwordError", e.getMessage());
			}

			IUserDAO userDAO = new UserDAO();

			if (userDAO.isUserExisting(email)) {
				request.setAttribute("emailError", "This email is already taken!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
				dispatcher.forward(request, response);

			}
			if (!password.equals(password2)) {
				request.setAttribute("passwordMissmatch", "Passwords are different!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
				dispatcher.forward(request, response);
			}
			userDAO.createUser(user);
			request.getRequestDispatcher("./jsp/LogIn.jsp").forward(request, response);

		}

	}

}
