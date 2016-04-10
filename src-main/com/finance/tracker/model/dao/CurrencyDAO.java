package com.finance.tracker.model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.finance.tracker.model.Currency;
import com.finance.tracker.model.ExchangeRate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CurrencyDAO {

	private static final int HTTP_SUCCESS = 200;

	public ExchangeRate getRate(Currency currency, Currency source) throws Exception {
		String url = "http://api.fixer.io/latest?base=" + source + "&symbols=" + currency;
		BufferedReader reader = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() == HTTP_SUCCESS) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String line = reader.readLine();
				StringBuilder builder = new StringBuilder();
				while (line != null) {
					builder.append(line);
					line = reader.readLine();
				}

				Gson gson = new Gson();
				JsonObject jsonObj = gson.fromJson(builder.toString(), JsonObject.class);
				System.out.println(jsonObj);
				ExchangeRate data = new ExchangeRate();
				String name = currency.toString();
				double rate = jsonObj.getAsJsonObject("rates").get(name).getAsDouble();
				System.out.println(rate + "@@@");
				data.setRate(rate);
				data.setBase(source);
				data.setToGet(currency);
				return data;
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		throw new Exception();
	}

	public double convert(int sum, Currency currency, Currency source) {
		String url = "http://api.fixer.io/latest?base=" + source + "&symbols=" + currency;
		BufferedReader reader = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() == HTTP_SUCCESS) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String line = reader.readLine();
				StringBuilder builder = new StringBuilder();
				while (line != null) {
					builder.append(line);
					line = reader.readLine();
				}

				Gson gson = new Gson();
				JsonObject jsonObj = gson.fromJson(builder.toString(), JsonObject.class);
				System.out.println(jsonObj);
				String name = currency.toString();
				double rate = jsonObj.getAsJsonObject("rates").get(name).getAsDouble();
				System.out.println(rate + "RATE");
				return sum * rate;
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public ExchangeRate getManyRates(List<Currency> currencies, Currency source) throws Exception {
		StringBuilder all = new StringBuilder();
		for (int index = 0; index < currencies.size() - 1; index++) {
			all.append(currencies.get(index));
			all.append(",");
		}
		Currency last = currencies.get(currencies.size() - 1);
		all.append(last);
		String url = "http://api.fixer.io/latest?base=" + source + "&symbols=" + all;
		BufferedReader reader = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() == HTTP_SUCCESS) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String line = reader.readLine();
				StringBuilder builder = new StringBuilder();
				while (line != null) {
					builder.append(line);
					line = reader.readLine();
				}

				Gson gson = new Gson();
				JsonObject jsonObj = gson.fromJson(builder.toString(), JsonObject.class);
				ExchangeRate data = new ExchangeRate();
				Map<Currency, Double> rates = new HashMap<Currency, Double>();

				for (Currency c : currencies) {
					String name = c.toString();
					double rate = (jsonObj.getAsJsonObject("rates").get(name).getAsDouble());
					rates.put(c, rate);
				}
				data.setBase(source);
				data.setManyResults(rates);
				System.out.println(jsonObj);

				return data;
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		throw new Exception();
	}
}
