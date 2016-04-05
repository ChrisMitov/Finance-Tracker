package com.finance.tracker.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.model.dao.TagDao;

@WebServlet("/tag/remove")
public class RemoveTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if
		// (!super.isAuthenticated(request))
		// {
		// response.sendRedirect("./menu");
		// return;
		// }

		int tagId = Integer.parseInt(request.getParameter("id"));
		// HttpSession session = request.getSession();
		// User user = (User)
		// session.getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME);
		// user.getShoppingCart().removeProduct(productId);
//		ICategory category = new CategoryDao().foundById(categoryId);
		new TagDao().removeTag(tagId);
		response.sendRedirect("../tags");
	}

}
