package com.finance.tracker.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.model.IUser;
import com.finance.tracker.model.dao.LogInDAO;
import com.finance.tracker.model.dao.UserDAO;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
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
//		String hashedPassowrd = null;
//		 try {
//			 hashedPassowrd = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
//		    } catch (NoSuchAlgorithmException e) {
//		        e.printStackTrace();
//		    }

		if (new LogInDAO().validate(username, password)) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(-1);
			int userId = userDao.getUserId(username);
			session.setAttribute("userId", userId);
			IUser user = userDao.getUser(userId);
			session.setAttribute("currentUser", user);
			session.setAttribute("userName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("currency", user.getCurrency());
			session.setAttribute("startDate", user.getJointedDate());
			request.getRequestDispatcher("./jsp/Profile.jsp").forward(request, response);
		} else {
			request.setAttribute("wrongUser", "Incorrect email or password!");
			request.getRequestDispatcher("./jsp/LogIn.jsp").forward(request, response);
		}

	}

}
