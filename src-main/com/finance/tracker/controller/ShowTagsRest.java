package com.finance.tracker.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.TagDao;
import com.google.gson.Gson;

/**
 * Servlet implementation class ShowTagsRest
 */
@WebServlet("/showTags")
public class ShowTagsRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Gson gson = new Gson();
//		String mring = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
//		int id = Integer.parseInt(request.getParameter("1104"));
		Collection<Tag> tags = new TagDao().getAllTagsByCategory(new CategoryDao().foundById(1104));
		response.getWriter().println(new Gson().toJson(tags));
	}


}
