package com.finance.tracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.tracker.model.Currency;
import com.finance.tracker.model.ExchangeRate;
import com.finance.tracker.model.dao.CurrencyDAO;

@WebServlet("/exchangeRates")
public class CurrencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Currency> toConvert = new ArrayList<Currency>();
			toConvert.add(Currency.EUR);
			toConvert.add(Currency.USD);
			Currency base = Currency.BGN;
			ExchangeRate rate = new CurrencyDAO().getManyRates(toConvert, base);
			Map<Currency, Double> rates = rate.getManyResults();
			request.getSession().setAttribute("base", base);
			request.getSession().setAttribute("rates", rates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("./jsp/partials/showRates.jsp").forward(request, response);
	}

}
