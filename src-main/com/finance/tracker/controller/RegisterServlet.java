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
//		String hashedPassword = hashPassword(password);

		if (password.equals(password2)) {

			IUser user = null;
			try {
				user = new User(firstName, lastName, password, email);
			} catch (PasswordException | FinanceTrackerException e) {
				request.setAttribute("passwordError", e.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
				dispatcher.forward(request, response);
			}

			IUserDAO userDAO = new UserDAO();

			if (userDAO.isUserExisting(email)) {
				request.setAttribute("emailError", "This email is already taken!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
				dispatcher.forward(request, response);

			} else {
				userDAO.createUser(user);
				request.getRequestDispatcher("./jsp/LogIn.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("passwordMissmatch", "Passwords are different!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/Register.jsp");
			dispatcher.forward(request, response);
		}

	}

//	private String hashPassword(String password) {
//		String passwordToHash = password;
//		String generatedPassword = null;
//		try {
//			// Create MessageDigest instance for MD5
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			// Add password bytes to digest
//			md.update(passwordToHash.getBytes());
//			// Get the hash's bytes
//			byte[] bytes = md.digest();
//			// This bytes[] has bytes in decimal format;
//			// Convert it to hexadecimal format
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < bytes.length; i++) {
//				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//			}
//			// Get complete hashed password in hex format
//			generatedPassword = sb.toString();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return generatedPassword;
//	}

}
