package com.finance.tracker.controller;

import java.io.IOException;

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

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}
		request.getRequestDispatcher("./LogIn.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (new LogInDAO().validate(username, password)) {
			HttpSession session = request.getSession();
			UserDAO userDao = new UserDAO();
			int userId = userDao.getUserId(username);
			session.setAttribute("userId", userId);
			IUser user = userDao.getUser(userId);
			session.setAttribute("currentUser", user);
			session.setAttribute("userName", user.getFirstName());
			request.getRequestDispatcher("./home.jsp").forward(request, response);
		} else {
			request.setAttribute("wrongUser", "Incorrect email or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/LogIn.jsp");
			dispatcher.forward(request, response);
		}

	}

}
